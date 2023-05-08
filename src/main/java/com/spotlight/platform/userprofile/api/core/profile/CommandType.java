package com.spotlight.platform.userprofile.api.core.profile;

public enum CommandType{
    Replace("replace"),
    Increment("increment"),
    Collect("collect");

    private final String _command;       

    private CommandType(String command) {
        _command = command;
    }

    public boolean equalsName(String otherCommand) {
        // (otherName == null) check is not needed because name.equals(null) returns false 
        return _command.equals(otherCommand);
    }

    public String toString() {
       return this._command;
    }
}