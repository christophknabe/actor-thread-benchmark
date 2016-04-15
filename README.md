# actor-thread-benchmark
## Benchmark for comparing efficiency of Java thread switching to Scala Akka actor switching
Christoph Knabe, 2016-04-15

## Massive Parallel Programming with Actors

Massive parallel programming is possible with actors. The first edition of "Programming in Scala" says in chapter 30.4:

> Unfortunately, despite their light-sounding name, threads are not all that
cheap in Java. Threads use enough memory that typical Java virtual machines
can have millions of objects but only thousands of threads. Worse,
switching threads often takes hundreds if not thousands of processor cycles.
If you want your program be as efficient as possible, then it is important to
be sparing with thread creation and thread switching.
                                                                
This benchmark example builds a chain of *number* parallel

* Threads (in Java), which are communicating by synchronized buffers, or 
* Actors (in Scala with Akka), which are communicating by sending messages, respectively.

Then it sends *number* messages through the chain, resulting in square of *number* switchings
between the threads respectively actors.

The measured times in milliseconds are:

<table border="1" style="text-align:right;">
  <tr>
    <th>number</th>
    <th>Threads [ms]</th>
    <th>Actors [ms]</th>
  </tr>
    <tr>
        <td>10</td>
        <td>9</td>
        <td>34</td>
    </tr>
    <tr>
        <td>100</td>
        <td>85</td>
        <td>220</td>
    </tr>
    <tr>
        <td>1,000</td>
        <td>5,390</td>
        <td>2,470</td>
    </tr>
    <tr>
        <td>10,000</td>
        <td>529,856</td>
        <td>37,988</td>
    </tr>
</table>

Measured on a AMD Quad-Core processor FX7500 with up to 3.3 GHz in Eclipse at 2016-04-15.

For benchmarking threads on your computer run ThreadBufferChainMain.java. 
It runs the Thread benchmark with chains of lengths 10, 100, 1000, and 10000.
Go away from your computer for about 600 seconds. 

For benchmarking actors you have to run ActorMessageChainMain.scala.
You will be prompted for the number of actors to be chained.
Repeat this for 10, 100, 1000, and 10000.

