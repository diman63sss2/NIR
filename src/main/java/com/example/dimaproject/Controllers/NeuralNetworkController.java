package com.example.dimaproject.Controllers;


import com.example.dimaproject.ArgsNeuronPojo;
import com.example.dimaproject.Services.NeuralNetworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NeuralNetworkController {

    private final NeuralNetworkService neuralNetworkService;


    @PostMapping("/callPython")
    public ResponseEntity<?> callPython(@RequestBody List<ArgsNeuronPojo> argsNeuronPojoList) throws IOException {


        byte[] bytes = neuralNetworkService.callPythonScript(argsNeuronPojoList);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(bytes);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test (){
        List<ArgsNeuronPojo> argsNeuronPojoList = new ArrayList<>();
        argsNeuronPojoList.add(new ArgsNeuronPojo("abc", 1.0, 2.0, 3.0, 4.0, 150L ));
        argsNeuronPojoList.add(new ArgsNeuronPojo("bcd", 1.0, 2.0, 3.0, 4.0, 150L ));

        System.out.println( argsNeuronPojoList.toString());;

        return ResponseEntity.status(HttpStatus.OK).body(argsNeuronPojoList);

    }
}
