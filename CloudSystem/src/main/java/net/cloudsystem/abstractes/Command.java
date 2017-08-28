package net.cloudsystem.abstractes;

import net.cloudsystem.CloudSystem;

/*
 * @author JoniP2804
 * @Copyright by JoniP2804, 24.08.2017, 17:13:07
 * @Created on 24.08.2017, 17:13:07
 */
public abstract class Command {

    public Command() {
        CloudSystem.list.add(this);
    }

    public void execute(String[] args) {}

}
