---
title: Emacs as an operating system
date: 2016-04-14
modified: 2016-05-29
---

Emacs is sometimes jokingly called a good operating system with a bad
text editor. Over the last year, I found myself using more and more of
Emacs, so I decided to try out how much of an operating system it
is. Of course, operating system here is referring to the programs that
the user interacts with, although I would love to try out some sort of
Emacs-based kernel.

<!--more-->

# Emacs as a terminal emulator / multiplexer

Terminals are all about text, and Emacs is all about text as well. Not
only that, but Emacs is also very good at running other processes and
interacting with them. It is no surprise, I think, that Emacs works
well as a terminal emulator.

Emacs comes out of the box with `shell` and `term`. Both of these
commands run the shell of your choice, and give you a buffer to
interact with it. Shell gives you a more emacs-y experience, while
term overrides all default keymaps to give you a full terminal
experience.

![Emacs as a terminal emulator](/img/emacs-terminal.png)

To use emacs as a full terminal, you can bind these to a key in your
window manager. I'm using i3, and my keybinding looks like this:

```
bindsym $mod+Shift+Return exec --no-startup-id emacs --eval "(shell)"
```

You can also create a desktop file to have a symbol to run this on a
desktop environment. Try putting the following text in a file at
`~/.local/share/applications/emacs-terminal.desktop`:

```
[Desktop Entry]
Name=Emacs Terminal
GenericName=Terminal Emulator
Comment=Emacs as a terminal emulator.
Exec=emacs --eval '(shell)'
Icon=emacs
Type=Application
Terminal=false
StartupWMClass=Emacs
```

If you want to use term instead, replace `(shell)` above with `(term "/usr/bin/bash")`.

A very useful feature of terminal multiplexers is the ability to leave
the shell running, even after the terminal is closed, or the ssh
connection has dropped if you are connection over that. Emacs can also
achieve this with it's server-client mode. To use that, start emacs
with `emacs --daemon`, and then create a terminal by running
`emacsclient -c --eval '(shell)'`. Even after you close emacsclient,
since Emacs itself is still running, you can run the same command
again to get back to your shell.

One caveat is that if there is a terminal/shell already running, Emacs
will automatically open that whenever you try opening a new one. This
can be a problem if you are using Emacs in server-client mode, or want
to have multiple terminals in the same window. In that case, you can
either do `M-x rename-uniquely` to change the name of the existing
terminal, which will make Emacs create a new one next time, or you can
add that to hook in your `init.el` to always have that behaviour:

```lisp
(add-hook 'shell-mode-hook 'rename-uniquely)
(add-hook 'term-mode-hook 'rename-uniquely)
```

# Emacs as a shell

Of course, it is not enough that Emacs works as a terminal
emulator. Why not use Emacs as a shell directly, instead of bash/zsh?
Emacs has you covered for that too. You can use eshell, which is a
shell implementation, completely written in Emacs Lisp. All you need
to do is press `M-x eshell`.

![Eshell, Emacs shell](/img/eshell.png)

The upside is that eshell can evaluate and expand lisp expressions, as
well as redirecting the output to Emacs buffers. The downside is
however, eshell is not feature complete. It lacks some features such
as input redirection, and the documentation notes that it is
inefficient at piping output between programs.

If you want to use eshell instead of shell or term, you can replace
`shell` in the examples of terminal emulator section with `eshell`.

# Emacs as a mail cilent

[Zawinski's Law](http://www.catb.org/~esr/jargon/html/Z/Zawinskis-Law.html):
Every program attempts to expand until it can read mail. Of course, it
would be disappointing for Emacs to not handle mail as well.

Emacs already ships with some mail capability. To get a full
experience however, I'd recommend using
[mu4e](http://www.djcbsoftware.nl/code/mu/mu4e.html) (mu for emacs). I
have personally set up [OfflineIMAP](http://www.offlineimap.org/) to
retrieve my emails, and mu4e gives me a nice interface on top of that.

![mu4e, mail client](/img/mu4e.png)

I'm not going to talk about the configurations of these programs, I'd
recommend checking out their documentations. Before ending this
section, I also want to mention
[mu4e-alert](https://github.com/iqbalansari/mu4e-alert) though.

# Emacs as a feed reader (RSS/Atom)

Emacs handles feeds very well too. The packages I'm using here are
[Elfeed](https://github.com/skeeto/elfeed) and
[Elfeed goodies](https://github.com/algernon/elfeed-goodies). Emacs
can even show images in the feeds, so it covers everything I need from
a feed reader.

![Elfeed, feed reader](/img/elfeed.png)

# Emacs as a file manager

Why use a different program to manage your files when you can use
Emacs? Emacs ships with dired, as well as image-dired. This gives you
a file browser, with optional image thumbnail support.

# Emacs as a document viewer

Want to read a pdf? Need a program to do a presentation? Again, Emacs.

![Docview, document viewer](/img/docview.png)

Emacs comes with
[DocView](https://www.gnu.org/software/emacs/manual/html_node/emacs/Document-View.html)
which has support for PDF, OpenDocument and Microsoft Office files. It
works surprisingly well.

Also, [PDF Tools](https://github.com/politza/pdf-tools) brings even
more PDF viewing capabilities to Emacs, including annotations, text
search and outline. After installing PDF Tools, Emacs has become my
primary choice for reading PDF files.

# Emacs as a browser

Emacs comes out of box with
[eww](https://www.gnu.org/software/emacs/manual/html_node/eww/index.html#Top),
a text-based web browser with support for images as well.

![eww, browser](/img/eww.png)

Honestly, I don't think I'll be using Emacs to browse the web. But
still, it is nice that the functionality is there.

# Emacs as a music player

Emacs can also act as a music player thanks to
[EMMS](https://www.gnu.org/software/emms/), Emacs MultiMedia
System. If you are wondering, it doesn't play the music by itself but
instead uses other players like vlc or mpd.

It has support for playlists, and can show thumbnails as well. For the
music types, it supports whatever the players it uses support, which
means that you can basically use file type.

# Emacs as a IRC client

I don't use IRC a lot, but Emacs comes out of the box with support for
that as well thanks to
[ERC](https://www.emacswiki.org/emacs?action=browse;oldid=EmacsIrcClient;id=ERC).

![erc, Emacs IRC client](/img/erc.png)

# Emacs as a text editor

Finally, Emacs also can work well as a text editor.

Emacs is a pretty fine text editor out of the box, but I want to
mention some packages here.

First,
[multiple cursors](https://github.com/magnars/multiple-cursors.el). Multiple
cursors mode allows you to edit text at multiple places at the same
time.

I also want to mention
[undo-tree](http://www.dr-qubit.org/emacs.php#undo-tree). It acts like
a mini revision control system, allowing you to undo and redo without
ever losing any text.

Another great mode is
[iy-go-to-char](https://github.com/doitian/iy-go-to-char). It allows
you to quickly jump around by going to next/previous occurrances of a
character. It is very useful when you are trying to move around a
line.

[Ace Jump Mode](https://github.com/winterTTr/ace-jump-mode/) allows
you to jump around the visible buffers. It can jump around based on
initial characters of words, or jump to specific lines. It can also
jump from one buffer to another, which is very useful when you have
several buffers open in your screen.

![Ace Jump Mode](/img/ace-jump-mode.png)

Finally, I want to mention [ag.el](https://github.com/Wilfred/ag.el),
which is an Emacs frontend for the silver searcher. If you don't know
about ag, it is a replacement for grep that recursively searches
directories, and has some special handling for projects, and is very
fast.

# Emacs as an IDE

People sometimes compare Emacs to IDE's and complain that a text
editor such as Emacs doesn't have enough features. What they are
forgetting, of course, is that Emacs is an operating system, and we
can have an IDE in it as well.

There are different packages for every language, so I'll be only
speaking on language agnostic ones.

For interacting with git, [magit](http://magit.vc/) is a wonderful
interface.

![Magit, Git Porcelain](/img/magit.png)

For auto-completion, [Company mode](https://company-mode.github.io/)
works wonders. I rely heavily on completion while writing code, and
company mode has support for anything I tried writing.

If you like having your code checked as you type,
[flycheck](https://www.flycheck.org/) has you covered. It has support
for many tools and languages.

![Company Mode and Flycheck](/img/company-flycheck.png)
