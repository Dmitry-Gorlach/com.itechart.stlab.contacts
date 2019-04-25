package com.itechart.stlab.contacts.command;

import com.itechart.stlab.contacts.exception.CommandException;

public class ActionFactory {

    public static Command defineCommand(String value) throws CommandException{
        if (value == null || value.isEmpty()){
            throw new CommandException("command is null or empty");
        }

        try {
            return CommandType.valueOf(value.toUpperCase()).getCommand();
        }catch (IllegalArgumentException e){
            throw new CommandException("Error.Unsupported command",e);
        }
    }
}
