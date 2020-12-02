package ru.samgtu.erp.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.samgtu.erp.dto.ErrorDTO
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(ERPException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    fun handleApplicationException(request: HttpServletRequest, exception: ERPException): ErrorDTO {
        return ErrorDTO(
            exception.localizedMessage,
            request.requestURI,
            HttpStatus.BAD_REQUEST.toString()
        )
    }
}