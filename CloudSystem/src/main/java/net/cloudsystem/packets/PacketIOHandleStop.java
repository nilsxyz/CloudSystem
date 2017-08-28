package net.cloudsystem.packets;

import java.text.MessageFormat;
import net.cloudsystem.CloudSystem;
import net.cloudsystem.abstractes.Packet;
import net.skillcloud.misc.Server;

/*
 * @author JoniP2804
 * @Copyright by JoniP2804, 27.08.2017, 21:18:30
 * @Created on 27.08.2017, 21:18:30
 */
public class PacketIOHandleStop extends Packet {

    private final String name;

    public PacketIOHandleStop(String name) {
        this.name = name;
    }

    @Override
    public void execute() {
        if (!CloudSystem.processes.isEmpty()) {
            if (CloudSystem.processes.containsKey(name)) {
                CloudSystem.processes.keySet().stream().forEach(names -> {
                    if (names.equalsIgnoreCase(name)) {
                        Server server = CloudSystem.processes.get(names);
                        Process process = server.getProcess();
                        process.destroy();
                        CloudSystem.processes.remove(names);
                        System.out.println(MessageFormat.format("Der Process [{0}/{1}/{2}] wurde gestoppt!", server.getName(), server.getUuid(), server.getPort()));
                    }
                });
            } else {
                System.out.println("Der Process " + name + " nicht aktiv!");
            }
        } else {
            System.out.println("Es sind leider gerade keine Process da!");
        }
    }

}
