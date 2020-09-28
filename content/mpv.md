---
title: Motion Interpolation, 24 FPS to 60 FPS with mpv, VapourSynth and MVTools
date: 2015-07-18
modified: 2015-07-20
---

Watching videos at 60 FPS is great. It makes the video significantly smoother and much more enjoyable. Sadly, lots of movies and TV shows are still at 24 FPS. However, I recently discovered that it is actually possible to interpolate the extra frames by using motion interpolation, and convert a video from 24 FPS to 60 FPS in real time. While it is far from perfect, I think the visual artifacts are a reasonable tradeoff for high framerate.

<!--more-->

Firstly, what we need is mpv with VapourSynth enabled, and MVTools plugin for VapourSynth. VapourSynth must be enabled while compiling mpv. I adopted an AUR package [mpv-vapoursynth](https://aur4.archlinux.org/packages/mpv-vapoursynth/) which you can use if you are on Arch. Otherwise, all you need to do is use `--enable-vapoursynth` flag when doing `./waf --configure`. They explain the compilation on their [repository](https://github.com/mpv-player/mpv), so look into there if you are compiling yourself.

After that, we need MVTools plugin for VapourSynth. This is available on Arch via [vapoursynth-plugin-mvtools](https://www.archlinux.org/packages/community/x86_64/vapoursynth-plugin-mvtools/), otherwise you can find their repository [here](https://github.com/dubhater/vapoursynth-mvtools). There is also a [PPA for Ubuntu](https://launchpad.net/~djcj/+archive/ubuntu/vapoursynth) where you can find `vapoursynth-extra-plugins`, but I haven't used it myself so I can't comment on it.

After both of these are enabled, we need a script to use MVTools from VapourSynth. There is one written by Niklas Haas, which you can find here as [mvtools.vpy](https://github.com/haasn/gentoo-conf/blob/master/home/nand/.mpv/filters/mvtools.vpy). Personally, I tweaked the block sizes and precision to my liking, as well as removing the resolution limit he added. I'll put the modified version here:

```python
# vim: set ft=python:

import vapoursynth as vs
core = vs.get_core()
clip = video_in

dst_fps = display_fps
# Interpolating to fps higher than 60 is too CPU-expensive, smoothmotion can handle the rest.
while (dst_fps > 60):
    dst_fps /= 2

# Skip interpolation for 60 Hz content
if not (container_fps > 59):
    src_fps_num = int(container_fps * 1e8)
    src_fps_den = int(1e8)
    dst_fps_num = int(dst_fps * 1e4)
    dst_fps_den = int(1e4)
    # Needed because clip FPS is missing
    clip = core.std.AssumeFPS(clip, fpsnum = src_fps_num, fpsden = src_fps_den)
    print("Reflowing from ",src_fps_num/src_fps_den," fps to ",dst_fps_num/dst_fps_den," fps.")
    
    sup  = core.mv.Super(clip, pel=1, hpad=8, vpad=8)
    bvec = core.mv.Analyse(sup, blksize=8, isb=True , chroma=True, search=3, searchparam=1)
    fvec = core.mv.Analyse(sup, blksize=8, isb=False, chroma=True, search=3, searchparam=1)
    clip = core.mv.BlockFPS(clip, sup, bvec, fvec, num=dst_fps_num, den=dst_fps_den, mode=3, thscd2=12)

clip.set_output()
```

At this point, you should be able to try this out as suggested in the script. To set this up more permanently, I'd suggest placing this script as `~/.config/mpv/mvtools.vpy`, and also writing the following as `~/.config/mpv/mpv.conf`:

```
hwdec=no
vf=vapoursynth=~/.config/mpv/mvtools.vpy
```

Now, whenever you open mpv, it will always use motion interpolation.

The result is fairly good. I noticed some significant artifacts while watching anime, but it works well with movies. I'm guessing that it is harder to track the motion in animations since they are generally exaggerated.

One thing to keep in mind, however, is performance. With `rel=2`, viewing a 1080p video results in around 90% CPU usage across all cores and 1.6 GBs of ram on my Intel i7 4700MQ. With `rel=1`, CPU usage goes down to about 60% per core. This process is very heavy on the processor, and you may have trouble unless you have a fast CPU.
