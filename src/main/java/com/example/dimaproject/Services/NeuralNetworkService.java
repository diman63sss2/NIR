package com.example.dimaproject.Services;


import com.example.dimaproject.ArgsNeuronPojo;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class NeuralNetworkService {
    private final String  pythonVenvLocation = "C:\\Users\\user\\PycharmProjects\\pythonProject\\venv\\Scripts\\python.exe";
//    private final String  pythonScriptLocation = "B:\\Projects-backend-Java\\DemoProject\\src\\main\\java\\com\\example\\demoproject\\PythonScript\\Vgg19.py";
    private final String  pythonScriptLocation = "C:\\Users\\user\\PycharmProjects\\prog3\\prog.py ";


    private String createInputTxtDoc(List<ArgsNeuronPojo> argsNeuronPojoList){
        try(FileWriter writer = new FileWriter("input.txt", true))
        {
            for (ArgsNeuronPojo argsNeuronPojo : argsNeuronPojoList) {
                String date = argsNeuronPojo.getDate();
                String open = argsNeuronPojo.getOpen().toString();
                String high = argsNeuronPojo.getHigh().toString();
                String low = argsNeuronPojo.getLow().toString();
                String close = argsNeuronPojo.getClose().toString();
                String volume = argsNeuronPojo.getVolume().toString();

                StringBuilder res = new StringBuilder();
                res.append(date).append(" ");
                res.append(open).append(" ");
                res.append(high).append(" ");
                res.append(low).append(" ");
                res.append(close).append(" ");
                res.append(volume).append(" ");

                writer.write(res.toString());

                writer.append('\n');
            }

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        String res = argsNeuronPojoList.toString();
        System.out.println(res);
        return "input.txt";
    }

    public byte[] callPythonScript(List<ArgsNeuronPojo> argsNeuronPojoList) throws IOException {
        createInputTxtDoc(argsNeuronPojoList);

        String executionLine = pythonVenvLocation + " "  + pythonScriptLocation;

        CommandLine cmdLine = CommandLine.parse(executionLine);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);

        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(streamHandler);

        System.out.println(executor.getWorkingDirectory());
        int exitCode = executor.execute(cmdLine);


        String output = outputStream.toString();

        System.out.println(output);
        System.out.println(exitCode);

        return outputStream.toByteArray();
    }
}
