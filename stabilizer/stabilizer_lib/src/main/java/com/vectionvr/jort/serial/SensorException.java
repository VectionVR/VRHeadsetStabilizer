/**
 * Copyright (C) 2014 Bnome SPRL (info@bnome.be)
 *
 * This file is part of VectionVR Stabilizer library.
 *
 * VectionVR Stabilizer library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VectionVR Stabilizer library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VectionVR Stabilizer library.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.vectionvr.jort.serial;

/**
 * @author (Nicolas Chalon) n.chalon@bnome.be
 */
public class SensorException extends RuntimeException {

    static enum ExceptionType{
        READ_TIMEOUT,UNABLE_TO_CONNECT,CANNOT_WRITE,CANNOT_VALIDATE,CANNOT_READ, NO_ANSWER;
    }
    private ExceptionType exceptionType;
    
    SensorException(String message, Throwable cause, ExceptionType exceptionType) {
        super(message, cause);
        this.exceptionType = exceptionType;
    }

    SensorException(String message, ExceptionType exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }
    
    public ExceptionType getExceptionType() {
        return exceptionType;
    }
}
