# Deubbing Services with ML
* @Christhalinger
* Compiler Engineer, Used to work for Sun Microsystems

### JVM Engineer
* JVM compiler chipcode

## Graal VM
* Graal chip compiler
* Truffle (language runtimes)
* Native Image

## Twitter
* 10^3 services
* Own their own hardware and data centers
* hand tuning doesn't scale at all
* wasted CPU and also instances not running with enough resources

## Performance Tuning
* tune, measure, tweak parameters
* Bayesian optimization to iterate and find near-optima
* using this, the services should tune themselves with a blackbox

## Autotune
* BOaaS - beyesian optimization as a service
* Whetlab, Spearmint

## Graal
* Written in Java. He has another talk about using it in real life.
* optimizing for gc cycles