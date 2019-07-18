# Chaos Debugging
Finding and fixing microservice abnormalities
* Scott Cranton and Mitch Kelly
* solo.io

## The Problem
* Tooling is built out for monolithic single process applications
* Distributed applications have many processes, so code interactions are tougher to debug
* Debugging microservices is like a murder mystery. Whodunnit?

# Tools
## Squash
* Debugging with default mode and secure mode
* Default: Loads a container with a debugger and loads it into your running container by process id
* Secure: if you have write access to a namespace you can debug a process within it.
* Demo: VScode plugin
* It's open source and you can play with all of this. https://squash.solo.io
* But what can I do in production systems? Can't just set a breakpoint...

## Loop
* If we hit an error, capture error condition and dump logs from that case only
* It's coming. Not open source nor documented yet.

## Chaos Engineering Today
* Problems: language-specific libraries and code modification
* Glooshot: define expirements, intervals, gather metrics and stop it when condition reached