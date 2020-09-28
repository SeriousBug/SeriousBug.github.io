---
title: Emacs and extensibility
date: 2015-10-06
---

Update: I've put the small Emacs tools I have written to a
[gist](https://gist.github.com/91c38ddde617b98ffbcb). 

I have been using Emacs for some time, and I really love it. The
amount of power it has, and the customizability is incredible. What
other editor allow you to connect to a server over SSH and edit files,
which is what I am doing to write this post. How many editors or IDE's
have support for so many languages?

<!--more-->

One thing I didn't know in the past, however, is extensibility of
Emacs. I mean, I do use a lot of packages, but I had never written
Elisp and I didn't know how hard or easy it would be. But after
starting to learn Clojure a bit, and feeling more comfortable with
lots of parenthesis, I decided to extend Emacs a bit to make it fit
myself better.

The first thing I added is an "insert date" function. I use Emacs to
take notes during lessons -using Org-mode- and I start every note with
the date of the lesson. Sure, glancing at the date to the corner of my
screen and writing it down takes just a few seconds, but why not write
a command to do it for me? Here is what I came up with:

```commonlisp
(defun insert-current-date ()
  "Insert the current date in YYYY-MM-DD format."
  (interactive)
  (shell-command "date +'%Y-%m-%d'" t))
```

Now that was easy and convenient. And being able to write my first
piece of Elisp so easily was really fun, so I decided to tackle
something bigger.

It is not rare that I need to compile and run a single C file. Nothing
fancy, no libraries, no makefile, just a single C file to compile and
run. I searched around the internet like "Emacs compile and run C", but
couldn't find anything. I had been doing this by opening a shell in
Emacs and compiling/running the program, but again, why not automate
it?

The code that follows is not really good. "It works" is as good as it
gets really, and actually considering that this is the first
substantial Elisp I have written, that is pretty impressive -for the
language and Emacs, which are both very helpful and powerful- I think.


```commonlisp
(require 's)
(defun compile-run-buffer ()
  "Compile and run buffer."
  (interactive)
  (let* ((split-file-path (split-string buffer-file-name "/"))
         (file-name (car (last split-file-path)))
         (file-name-noext (car (split-string file-name "[.]")))
         (buffer-name (concat "compile-run: " file-name-noext))
         (buffer-name* (concat "*" buffer-name "*")))
    (make-comint buffer-name "gcc" nil "-Wall" "-Wextra" "-o" file-name-noext file-name)
    (switch-to-buffer-other-window buffer-name*)
    (set-process-sentinel (get-buffer-process (current-buffer))
                          (apply-partially
                           '(lambda (prog-name proc even)
                              (if (s-suffix? "finished\n" even)
                                  (progn
                                    (insert "Compilation successful.\n\n")
                                    (comint-exec (current-buffer) prog-name (concat "./" prog-name) nil nil))
                                (insert (concat "Compilation failed!\n" even))))
                           file-name-noext))))
```


Again, the code is not really good. I'm uploading it here right now
because I'm actually very excited that I wrote this. Just now I can
think of ways to improve this, for example moving the compiler and the
flags to variables so that they can be customized. I could also
improve the presentation, because strings printed by this function,
comint and the running programs mixes up. I'll update this blog post
if I get to updating the code.

If this is your first time hearing about Emacs, this post may look
very confusing. I don't to Emacs any justice here, so do check it out
somewhere like [Emacs rocks](http://emacsrocks.com/). On the other
hand, if you have been looking a functionality like this, hope this
helps. If you have any suggestions about the code, I'd love to hear
them, you can find my email on the "about me" page. Anyway, have a
good day!
