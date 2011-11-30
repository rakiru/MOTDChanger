MOTDChanger
====================

Changes the server MOTD as seen in your Minecraft server list.

Purpose
--------

Why make this plugin when you can just change it in server.properties? Well, many server lists, such as mtserverlist.com and servers.getspout.org, require you to change the MOTD briefly so they can verify that you are the real owner of the server. While it's possible to do without a plugin, it requires two reloads, which is just not acceptable for the majority of the bigger servers out there. And so, I created this wee thing. It doesn't save the MOTD when changed, so a reload will undo any changes it makes.

Commands
--------

To change the MOTD, use the command:
/motd New MOTD goes here

To change your motd for one minute, use the command:
/motd -t 1 New MOTD goes here