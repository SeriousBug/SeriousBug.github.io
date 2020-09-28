---
title: Taking Backups with Duplicity
date: 2015-05-16
---


I wanted to start taking backups for some time, but I haven't had the time to do any research and set everything up. After reading another [horror story that was saved by backups](https://www.reddit.com/r/linuxmasterrace/comments/35ljcq/couple_of_days_ago_i_did_rm_rf_in_my_home/), I decided to start taking some backups.

<!--more-->

After doing some research on backup options, I decided on [duplicity](http://duplicity.nongnu.org/). The backups are compressed, encrypted and incremental, both saving space and ensuring security. It supports both local and ssh files(as well as many other protocols), so it has everything I need.

I first took a backup into my external hard drive, then VPS. The main problem I encountered was that duplicity uses [paramiko](https://github.com/paramiko/paramiko) for ssh, but it wasn't able to negotiate a key exchange algorithm with my VPS. Luckily, duplicity also supports [pexpect](http://pexpect.sourceforge.net/pexpect.html), which uses OpenSSH. If you encounter the same problem, you just need to tell duplicity to use pexpect backend by prepending your url with `pexpect+`, like `pexpect+ssh://example.com`.

Duplicity doesn't seem to have any sort of configuration files of itself, so I ended up writing a small bash script to serve as a sort of configuration, and also keep me from running duplicity with wrong args. I kept forgetting to add an extra slash to `file://`, causing duplicity to backup my home directory into my home directory! :D

If anyone is interested, here's the script:

```bash
#!/bin/bash

if [[ $(id -u) != "0" ]]; then
    read -p "Backup should be run as root! Continue? [y/N]" yn
    case $yn in
        [Yy]*) break;;
        *) exit;;
    esac
fi


if [[ $1 = file://* ]]; then
    echo "Doing local backup."
	ARGS="--no-encryption"
    if [[ $1 = file:///* ]]; then
		URL=$1
    else
		echo "Use absolute paths for backup."
		exit 1
    fi
elif [[ $1 = scp* ]]; then
    echo "Doing SSH backup."
    ARGS="--ssh-askpass"
    URL="pexpect+$1"
else
    echo "Unknown URL, use scp:// or file://"
    exit 1
fi


if [[ -n "$1" ]]; then
    duplicity $ARGS --exclude-filelist /home/kaan/.config/duplicity-files /home/kaan "$URL/backup"
else
    echo "Please specify a location to backup into."
    exit 1
fi

```
