package com.ss.cities.client.errors;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HandlerExceptionResolver extends DefaultHandlerExceptionResolver {

    @Override
    protected ModelAndView handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request,
            HttpServletResponse response, Object handler) throws IOException {

        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return new ModelAndView();
    }

    @Override
    protected ModelAndView handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpServletRequest request,
            HttpServletResponse response, Object handler) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
        if (!CollectionUtils.isEmpty(mediaTypes)) {
            response.setHeader("Accept", MediaType.toString(mediaTypes));
        }
        return new ModelAndView();
    }

    @Override
    protected ModelAndView handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpServletRequest request,
            HttpServletResponse response, Object handler) throws IOException {

        response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
        return new ModelAndView();
    }

    @Override
    protected ModelAndView handleMissingPathVariable(MissingPathVariableException ex, HttpServletRequest request, HttpServletResponse response,
            Object handler) throws IOException {

        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ModelAndView();
    }

    @Override
    protected ModelAndView handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpServletRequest request,
            HttpServletResponse response, Object handler) throws IOException {

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    @Override
    protected ModelAndView handleServletRequestBindingException(ServletRequestBindingException ex, HttpServletRequest request,
            HttpServletResponse response, Object handler) throws IOException {

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    @Override
    protected ModelAndView handleConversionNotSupported(ConversionNotSupportedException ex, HttpServletRequest request, HttpServletResponse response,
            Object handler) throws IOException {
        sendServerError(ex, request, response);
        return new ModelAndView();
    }

    @Override
    protected ModelAndView handleTypeMismatch(TypeMismatchException ex, HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    @Override
    protected ModelAndView handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request, HttpServletResponse response,
            Object handler) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    @Override
    protected ModelAndView handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpServletRequest request, HttpServletResponse response,
            Object handler) throws IOException {
        sendServerError(ex, request, response);
        return new ModelAndView();
    }

    @Override
    protected ModelAndView handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request,
            HttpServletResponse response, Object handler) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    @Override
    protected ModelAndView handleMissingServletRequestPartException(MissingServletRequestPartException ex, HttpServletRequest request,
            HttpServletResponse response, Object handler) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    @Override
    protected ModelAndView handleBindException(BindException ex, HttpServletRequest request, HttpServletResponse response, Object handler)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    @Override
    protected ModelAndView handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request, HttpServletResponse response,
            Object handler) throws IOException {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return new ModelAndView();
    }

    @Override
    protected void sendServerError(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    protected ModelAndView accessForbidden(Exception ex, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return new ModelAndView();
    }

}
