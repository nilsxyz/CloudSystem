package net.cloudsystem.commands;

import java.text.MessageFormat;
import net.cloudsystem.CloudSystem;
import net.cloudsystem.abstractes.Command;
import net.skillcloud.misc.Server;

/*
 * @author JoniP2804
 * @Copyright by JoniP2804, 24.08.2017, 17:33:20
 * @Created on 24.08.2017, 17:33:20
 */

public class End extends Command {

    @Override
    public void execute(String[] args) {
        if (args[0].equalsIgnoreCase("end")) {
            if(!CloudSystem.processes.isEmpty()) {
                CloudSystem.processes.keySet().stream().forEach(name -> {
                    Server server = CloudSystem.processes.get(name);
                    Process process = server.getProcess();
                    process.destroy();
                    CloudSystem.processes.remove(name);
                    System.out.println(MessageFormat.format("Der Process [{0}/{1}/{2}] wurde gestoppt!", server.getName(), server.getUuid(), server.getPort()));
                });
                System.out.println("Die Processe wurden gestoppt!");
            }
            System.out.println("Das CloudSystem wird gestoppt!");
            System.exit(0);
        }
    }
}
