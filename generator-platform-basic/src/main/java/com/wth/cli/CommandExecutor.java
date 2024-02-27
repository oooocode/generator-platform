package com.wth.cli;

import com.wth.cli.command.ConfigCommand;
import com.wth.cli.command.GenerateCommand;
import com.wth.cli.command.ListCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * 命令执行器
 */
@Command(name = "wth", version = "0.01", mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable {

    private final CommandLine commandLine;

    {
        commandLine = new CommandLine(this)
                .addSubcommand(new GenerateCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand());
    }

    @Override
    public void run() {
        System.out.println("请输入命令! 帮助手册 --help");
    }

    public Integer doExecute(String[] args) {
        return commandLine.execute(args);
    }
}