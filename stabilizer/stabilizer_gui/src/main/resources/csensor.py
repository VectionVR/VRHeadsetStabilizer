from cgkit.cgtypes import *
from math import *
import struct
import time

orth_orients = [
    (0.0, 1.0, 0.0, 0.0),
    (0.0, 1.0, 0.0, 90.0),
    (0.0, 1.0, 0.0, 180.0),
    (0.0, 1.0, 0.0, 270.0),
    (0.0, 0.0, 1.0, 180.0),
    (0.707, 0.0, 0.707, 180.0),
    (1.0, 0.0, 0.0, 180.0),
    (-0.707, 0.0, 0.707, 180.0),
    (-1.0, 0.0, 0.0, 90.0),
    (-0.58, 0.58, 0.58, 120.0),
    (0.0, 0.707, 0.707, 180.0),
    (0.58, 0.58, 0.58, 240.0),   
    (0.0, 0.707, -0.707, 180.0),
    (0.58, 0.58, -0.58, 120.0),
    (1.0, 0.0, 0.0, 90.0),
    (-0.58, 0.58, -0.58, 240.0),
    (-0.58, 0.58, -0.58, 120.0),
    (-0.707, 0.707, 0, 180.0),
    (-0.58, 0.58, 0.58, 240.0),
    (0.0, 0.0, 1.0, 270.0),
    (-0.58, -0.58, 0.58, 120.0),
    (-0.707, -0.707, 0.0, 180.0),
    (-0.58, -0.58, -0.58, 240.0),
    (0.0, 0.0, -1.0, 270.0)
]

def apply_parameters(pt, params):
    bias = vec3(params[9:])
    scale = mat3(params[:9])
    
    return scale * (pt + bias)
    
def rate_parameters(params, samples, targets):    
    total_error = 0
    
    for i in range(len(samples)):
        sample = samples[i]
        target = targets[i]
        
        sample = apply_parameters(sample, params)
        
        error = target - sample
        total_error += error.length()
    return total_error
    
def format_params(params):
    outstr = ""
    for i in range(12):
        outstr += "%.4f" % params[i] + ','  
    return outstr[:-1]
    
def generate_target_list(origin):
    print origin

    target_list = []
    q_orth_orients = [quat(orth_orients[i][3]*pi/180.0, vec3(-orth_orients[i][0], -orth_orients[i][1], orth_orients[i][2])) for i in range(24)]
    for o in q_orth_orients:
        target_list.append(o.rotateVec(origin))
    return target_list
    
change_vectors = [
    [0,0,0,0,0,0,0,0,0,.1,0,0],
    [0,0,0,0,0,0,0,0,0,-.1,0,0],
    [0,0,0,0,0,0,0,0,0,0,.1,0],
    [0,0,0,0,0,0,0,0,0,0,-.1,0],
    [0,0,0,0,0,0,0,0,0,0,0,.1],
    [0,0,0,0,0,0,0,0,0,0,0,-.1],
    [.001,0,0,0,0,0,0,0,0,0,0,0],
    [-.001,0,0,0,0,0,0,0,0,0,0,0],
    [0,0,0,0,.001,0,0,0,0,0,0,0],
    [0,0,0,0,-.001,0,0,0,0,0,0,0],
    [0,0,0,0,0,0,0,0,.001,0,0,0],
    [0,0,0,0,0,0,0,0,-.001,0,0,0],
    [0,.0001,0,0,0,0,0,0,0,0,0,0],
    [0,-.0001,0,0,0,0,0,0,0,0,0,0],
    [0,0,.0001,0,0,0,0,0,0,0,0,0],
    [0,0,-.0001,0,0,0,0,0,0,0,0,0],
    [0,0,0,.0001,0,0,0,0,0,0,0,0],
    [0,0,0,-.0001,0,0,0,0,0,0,0,0],
    [0,0,0,0,0,.0001,0,0,0,0,0,0],
    [0,0,0,0,0,-.0001,0,0,0,0,0,0],
    [0,0,0,0,0,0,.0001,0,0,0,0,0],
    [0,0,0,0,0,0,-.0001,0,0,0,0,0],
    [0,0,0,0,0,0,0,.0001,0,0,0,0],
    [0,0,0,0,0,0,0,-.0001,0,0,0,0],
]
    
def calc_gradient_descent_params(samples, targets):  
    RATING_THRESHHOLD = 0#.25

    params = [1,0,0,0,1,0,0,0,1,0,0,0]

    last_rating = rate_parameters(params,samples,targets)

    print "Start", last_rating
    
    max_scale = 1000000000
    
    #start, limit, stage, scale
    change_vector_info = [0,6,0,max_scale]
    
    def advance_change_vector_stage(change_vector_info):
        change_vector_info[2] += 1
        if change_vector_info[2] > 2:
            return 0
        if change_vector_info[2] == 1:
            change_vector_info[0] = 0
            change_vector_info[1] = 12
            change_vector_info[3] = max_scale
        elif change_vector_info[2] == 2:
            change_vector_info[0] = 0
            change_vector_info[1] = 24
            change_vector_info[3] = max_scale
        return 1
    
    count = 1
    while 1:#for count in range(5000):
        best_rating = last_rating
        best_parameters = None
        for ci in range(change_vector_info[0], change_vector_info[1]):
            c = change_vectors[ci]
            new_params = [params[i]+c[i]*change_vector_info[3] for i in range(len(c))]
            #print new_params, compass_params
            rating = rate_parameters(new_params,samples,targets)
            
            if rating < best_rating:
                best_parameters = new_params
                best_rating = rating
                
        if count % 100 == 0:
            print "Round", count, ":", best_rating, change_vector_info[3]
        count += 1
        
        #if count == 3000:
        #    break

        if count == 2000 and change_vector_info[2] < 2:
            print "Change to 2 from count"
            advance_change_vector_stage(change_vector_info)
        elif count == 1000 and change_vector_info[2] < 1:
            print "Change to 1 from count"
            advance_change_vector_stage(change_vector_info)
                
        if best_rating >= last_rating - RATING_THRESHHOLD:
            if change_vector_info[3] == 1:
                print "Change from exhaustion"
                if not advance_change_vector_stage(change_vector_info):
                    break
            else:
                change_vector_info[3] *= .1
                if change_vector_info[3] < 1:
                    change_vector_info[3] = 1
                #print scale
        else:
            change_vector_info[3] *= 1.1
        if best_parameters:
            last_rating = best_rating
            params = best_parameters
        
        
        #print scale
        
    print "Grad rating:", last_rating        
    print "Grad params:", format_params(params)
    
    return params