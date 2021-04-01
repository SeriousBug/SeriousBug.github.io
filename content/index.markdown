I'm a 4th year Computer Science PhD student at the Ohio State University. Over
the duration of my PhD, I have been researching topics in Programming Languages
such as data race detection, and transactional persistent memory. Research that
I led or assisted resulted in several publications, which are listed below. I
also had the opportunity to teach the course Advanced C Programming, and serve
on the Artifact Evaluation Committees of several conferences.

Outside the school, I build web and mobile applications for fun. Over the years
I have learned and used many programming languages and technologies, including
JavaScript, TypeScript, React, React Native, Python, Java, C, C++, Clojure, and
Haskell. All my projects are open source, and are available on my [Github
page](https://github.com/SeriousBug).

I am an avid Linux user, having been using it full time for the last 7 years. I
manage several small personal servers, and have experience with Bash scripting,
writing SystemD services, and building and using Docker containers.


# Highlighted Projects


<div> <div class="publication">
## image-survey

A web application to run surveys where users compare 2 images to pick the one
they prefer. Uses a Python backend utilizing asyncio capabilities with the Sanic
web server and SQLite. The front end is a React app, written in JavaScript and
Material-UI.

[Github](https://github.com/SeriousBug/image-survey) [Demo](https://imagesurvey.kaangenc.me/)
</div>


<div class="publication">
## WorkTime

Work-in-progress offline-first mobile app for productivity and time management.
Uses React Native with TypeScript, and a PouchDB database on the client side.
Currently planning to add a backend supported by CouchDB to provide
synchronization capabilities.

[Github](https://github.com/SeriousBug/WorkTime)
</div>


<div class="publication">
## Crafty

A transactional persistent memory library, written in C. Provides transactional
data storage capabilities to programs by utilizing existing hardware
transactional memory support of processors, combined with the new non-volatile
memory hardware. Allows programs to store and access data efficiently, and
without the need for additional concurrency synchronization. This is the
corresponding implementation for one of my publications listed below.

[Github](https://github.com/PLaSSticity/Crafty)
</div>


<div class="publication">
## SDP & WDP data race detectors

Data race detection for Java programs using predictive dynamic data race
analyses. Predictive race analysis looks at a single execution of a program to
detect races that may occur in many other executions. Our analyses use data and
control flow dependence to find more data races compared to other analyses. This
is the corresponding implementation for my publication "Dependence Aware,
Unbounded Sound Predictive Race Detection".

[Github](https://github.com/PLaSSticity/SDP-WDP-implementation)
</div>

</div>



# Publications

<div> <div class="publication">
## Crafty: Efficient, HTM-Compatible Persistent Transactions
<div class="authors">Kaan Genç, Michael D. Bond, and Guoqing Harry Xu</div>
<div class="conf">ACM SIGPLAN Conference on Programming Language Design and Implementation <a href="https://pldi20.sigplan.org/home">(PLDI 2020)</a>, Online, June 2020</div>

[Talk](https://www.youtube.com/watch?v=wdVLlQXV1to) [Paper](https://dl.acm.org/doi/10.1145/3385412.3385991) [Extended Paper](https://arxiv.org/pdf/2004.00262.pdf) [Implementation](https://github.com/PLaSSticity/Crafty) [Poster](/extra/Crafty Poster.pdf)
</div>


<div class="publication">
## Dependence Aware, Unbounded Sound Predictive Race Detection
<div class="authors">Kaan Genç, Jake Roemer, Yufan Xu, and Michael D. Bond</div>
<div class="conf">ACM SIGPLAN International Conference on Object-Oriented Programming, Systems, Languages, and Applications <a href="https://2019.splashcon.org/track/splash-2019-oopsla">(OOPSLA 2019)</a>, Athens, Greece, October 2019</div> 

[Talk](https://www.youtube.com/watch?v=YgZWnc31tVQ) [Paper](https://dl.acm.org/doi/10.1145/3360605) [Extended Paper](https://arxiv.org/pdf/1904.13088.pdf) [Implementation](https://github.com/PLaSSticity/SDP-WDP-implementation) [Poster](/extra/DepAware Poster.pdf)
</div>


<div class="publication">
## SmartTrack: Efficient Predictive Race Detection
<div class="authors">Jake Roemer, Kaan Genç, and Michael D. Bond</div>
<div class="conf">ACM SIGPLAN Conference on Programming Language Design and Implementation <a href="https://pldi20.sigplan.org/home">(PLDI 2020)</a>, Online, June 2020 </div>

[Paper](http://web.cse.ohio-state.edu/~mikebond/smarttrack-pldi-2020.pdf) [Extended Paper](https://arxiv.org/pdf/1905.00494.pdf)
</div>


<div class="publication">
## High-Coverage, Unbounded Sound Predictive Race Detection 
<div class="authors">Jake Roemer, Kaan Genç, and Michael D. Bond</div>
<div class="conf">ACM SIGPLAN Conference on Programming Language Design and Implementation <a href="https://pldi18.sigplan.org/">(PLDI 2018)</a>, Philadelphia, PA, USA, June 2018</div>

[Paper](http://web.cse.ohio-state.edu/~bond.213/vindicator-pldi-2018.pdf) [Extended Paper](http://web.cse.ohio-state.edu/~bond.213/vindicator-pldi-2018-xtr.pdf)
</div>
</div>

# Activities

[PLDI 2021](https://pldi21.sigplan.org/track/pldi-2021-PLDI-Research-Artifacts) Artifact Evaluation Committee member

[ASPLOS 2021](https://asplos-conference.org/2021/) Artifact Evaluation Committee member

[OOPSLA 2020](https://2020.splashcon.org/track/splash-2020-Artifacts) Artifact Evaluation Committee member


<div class="other-stuff">
# Other stuff

<a class="img" href="https://seriousbug.itch.io/the-land-itself">
![A picture from a 3-D video game, showing a river flowing into the sea. The text "The Land Itself" is overlayed on the sea.](/img/game-cover.jpg)\
</a>

In my free time, I develop small indie video games and release them open source.
</div>
