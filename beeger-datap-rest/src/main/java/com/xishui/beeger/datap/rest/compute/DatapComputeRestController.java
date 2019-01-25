package com.xishui.beeger.datap.rest.compute;

import com.xishui.beeger.datap.model.compute.ComputeRequestModel;
import com.xishui.beeger.datap.model.compute.ComputeResponseModel;
import com.xishui.beeger.datap.rest.processor.ComputeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class DatapComputeRestController {
    private final Logger logger = LoggerFactory.getLogger(DatapComputeRestController.class);

    @RequestMapping(value = "/compute", method = RequestMethod.POST)
    public ComputeResponseModel compute(HttpServletRequest request, HttpServletResponse response, @RequestBody ComputeRequestModel computeRequestModel) {
        return new ComputeProcessor().processor(computeRequestModel);
    }

}
