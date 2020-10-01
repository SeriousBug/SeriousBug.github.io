---
title: Switching to pass
date: 2015-03-30
---

For some time, I used LastPass to store my passwords. While LastPass works well, it doesn't fit into the keyboard driven setup I have. I have been looking into alternatives for some time, I looked into KeePassX but just like LastPass, it doesn't give me any ways to set up keyboard shortcuts. On the other hand, and I recently came across [pass](http://www.passwordstore.org/), and it provides everything I want.

<!--more-->

Pass uses GPG keys to encrypt the passwords, and git to keep revisions and backups. It integrates well with the shell, and there is a dmenu script, a Firefox plugin and an Android app. All the passwords are just GPG enrypted files, stored in some folders anyway, so you don't need anything special to work with them.

![passmenu, the dmenu pass script](/img/passmenu.png)

So first, I needed to migrate my passwords from LastPass to pass. The website lists some scripts for migration, but sadly I missed that when I first looked at the page. So I decided to write a [python script to handle the migration](https://gist.github.com/SeriousBug/e9f33873d10ad944cbe6) myself. It inserts all passwords in `domain/username` format, and if there is any extra data written, it is added after the password as well. Secure notes are placed into their own folder, and any "Generated Password for ..." entries are skipped. If you're migrating from LastPass to pass, feel free to give it a try. If you are taking an export from their website however, do make sure that there is no whitespace before and after the csv.

![Password Store, the pass Android app](/img/password_store.png)

I certainly recommend trying out pass. It works very well, and it fits in with the unix philosophy.
