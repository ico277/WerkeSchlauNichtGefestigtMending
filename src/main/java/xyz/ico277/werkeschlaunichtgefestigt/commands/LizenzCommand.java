package xyz.ico277.werkeschlaunichtgefestigt.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class LizenzCommand extends CommandBase {
    private String lizenz;

    public LizenzCommand() {
        InputStream inputStream = LizenzCommand.class.getClassLoader().getResourceAsStream("LICENSE");
        if (inputStream == null) {
            throw new IllegalArgumentException("Konnte Lizenz-Datei nicht finden!!!");
        }

        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (Exception e) {
            lizenz = "Fehler: Konnte Lizenz-Datei nicht lesen!!!";
            return;
        }

        lizenz = result.toString();
    }

    @Override
    public String getName() {
        return "WerkeSchlauNichtGefestigtMending";
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "/WerkeSchlauNichtGefestigtMending lizenz";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1) {
            throw new CommandException("Usage: " + getUsage(sender));
        }
        if (!args[0].equalsIgnoreCase("lizenz")) {
            throw new CommandException("Usage: " + getUsage(sender));
        }

        sender.sendMessage(new TextComponentString(lizenz));
    }
}
