package com.fengwenyi.erwin.component.os.client;

import com.fengwenyi.api.result.ResultTemplate;
import com.fengwenyi.javalib.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-07-08
 */
@RestController
@RequestMapping("/osClient")
@Slf4j
public class OsClientController {

    @PostMapping("/execute")
    public ResultTemplate<List<String>> execute(String param) throws IOException, InterruptedException {

        String str = StringUtils.hasText(param) ? param : "cd /usr/local/apps/windrunner/windrunner-member\nsh build-docker-compose.sh";

        FileUtils.write("build.sh",str, true);

        execCommand("sh", "build.sh");

        return ResultTemplate.success();
    }

    public void execCommand(String... command) throws IOException, InterruptedException {
        List<String> commands = Arrays.asList(command);
        // 调用start()方法来执行命令
        ProcessBuilder pb = new ProcessBuilder(commands);
        Process process = pb.start();
        // 通过process.getInputStream()获取Linux命令的执行结果的输入流，构造InputStreamReader对象将其转化为字符流
        // 再通过BufferedReader对其进行缓冲区处理，提高读取效率
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line = null;
        // 循环读取每一行命令执行结果
        while ((line = reader.readLine()) != null) {
            // 打印每一行命令执行结果到控制台
            log.info(line);
//            if (StringUtils.hasText(line) && line.contains("process running for")) {
//                break;
//            }
        }
        // 等待进程结束
//        int exitCode = process.waitFor();
//        if (exitCode != 0) {
//            throw new RuntimeException("Command exited with non-zero status code: " + exitCode);
//        }
    }

    public void bak1() throws IOException {
        List<String> commands = Arrays.asList("cd /usr/local/apps/windrunner/windrunner-member & sh build-docker-compose.sh");
        // 调用start()方法来执行命令
        ProcessBuilder pb = new ProcessBuilder(commands);
        Process process = pb.start();
        // 通过process.getInputStream()获取Linux命令的执行结果的输入流，构造InputStreamReader对象将其转化为字符流
        // 再通过BufferedReader对其进行缓冲区处理，提高读取效率
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line = null;
        List<String> result = new ArrayList<>();
        // 循环读取每一行命令执行结果
        while ((line = reader.readLine()) != null) {
            // 打印每一行命令执行结果到控制台
            log.info(line);
            result.add(line);
//            if (StringUtils.hasText(line) && line.contains("process running for")) {
//                break;
//            }
        }
        // 等待进程结束
//        int exitCode = process.waitFor();
//        if (exitCode != 0) {
//            throw new RuntimeException("Command exited with non-zero status code: " + exitCode);
//        }
    }

    public void bak3() throws IOException {
        Process process1 = Runtime.getRuntime().exec("cd /usr/local/apps/windrunner/windrunner-member");
        // 通过process.getInputStream()获取Linux命令的执行结果的输入流，构造InputStreamReader对象将其转化为字符流
        // 再通过BufferedReader对其进行缓冲区处理，提高读取效率
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(process1.getInputStream()));

        String line1 = null;
        // 循环读取每一行命令执行结果
        while ((line1 = reader1.readLine()) != null) {
            // 打印每一行命令执行结果到控制台
            log.info(line1);
//            if (StringUtils.hasText(line) && line.contains("process running for")) {
//                break;
//            }
        }
        // 等待进程结束
//        int exitCode = process.waitFor();
//        if (exitCode != 0) {
//            throw new RuntimeException("Command exited with non-zero status code: " + exitCode);
//        }

        Process process2 = Runtime.getRuntime().exec("sh build-docker-compose.sh");
        // 通过process.getInputStream()获取Linux命令的执行结果的输入流，构造InputStreamReader对象将其转化为字符流
        // 再通过BufferedReader对其进行缓冲区处理，提高读取效率
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(process2.getInputStream()));

        String line2 = null;
        // 循环读取每一行命令执行结果
        while ((line2 = reader2.readLine()) != null) {
            // 打印每一行命令执行结果到控制台
            log.info(line2);
//            if (StringUtils.hasText(line) && line.contains("process running for")) {
//                break;
//            }
        }
        // 等待进程结束
//        int exitCode = process.waitFor();
//        if (exitCode != 0) {
//            throw new RuntimeException("Command exited with non-zero status code: " + exitCode);
//        }
    }

}
