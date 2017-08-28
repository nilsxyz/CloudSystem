package net.cloudsystem.commands;

import net.cloudsystem.CloudSystem;
import net.cloudsystem.abstractes.Command;
import net.skillcloud.misc.Server;

/*
 * @author JoniP2804
 * @Copyright by JoniP2804, 24.08.2017, 17:15:14
 * @Created on 24.08.2017, 17:15:14
 */
public class ProcessList extends Command {

    @Override
    public void execute(String[] args) {
        if (args[0].equalsIgnoreCase("processlist")) {
            if (!CloudSystem.processes.isEmpty()) {
                System.out.println("---------------");
                CloudSystem.processes.keySet().stream().forEach(servers -> {
                    Server server = CloudSystem.processes.get(servers);
                    System.out.println("> " + server.getName());
                });
                System.out.println("---------------");
            } else {
                System.out.println("Es gibt keine Processe");
            }
        }
    }

}
