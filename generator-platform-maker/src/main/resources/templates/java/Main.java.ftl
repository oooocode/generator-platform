package ${basePackage};

import ${basePackage}.cli.CommandExecutor;

/**
 * @Author: ${author}
 * @Create: ${createTime}
 */
public class Main {


    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}
