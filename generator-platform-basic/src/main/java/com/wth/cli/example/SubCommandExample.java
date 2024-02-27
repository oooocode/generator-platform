package com.wth.cli.example;

import picocli.CommandLine;

/**
 * @Author: wth
 * @Create: 2024/2/27 - 0:05
 */
@CommandLine.Command(name = "main", mixinStandardHelpOptions = true)
public class SubCommandExample implements Runnable{


    @Override
    public void run() {
        System.out.println("执行主命令");
    }

    @CommandLine.Command(name = "add",description = "添加", mixinStandardHelpOptions = true)
    static class Add implements Runnable{
        @Override
        public void run() {
            System.out.println("添加");
        }
    }

    @CommandLine.Command(name = "delete", description = "删除", mixinStandardHelpOptions = true)
    static class Delete implements Runnable{
        @Override
        public void run() {
            System.out.println("删除");
        }
    }

    @CommandLine.Command(name = "query", description = "查询", mixinStandardHelpOptions = true)
    static class Query implements Runnable{
        @Override
        public void run() {
            System.out.println("查询");
        }
    }

    public static void main(String[] args) {
        // args = new String[] {};
        // args = new String[] {"add", "--help"};
        // args = new String[] {"--help"};
        new CommandLine(new SubCommandExample())
                .addSubcommand(new Add())
                .addSubcommand(new Delete())
                .addSubcommand(new Query())
                .execute(args);
    }
}
