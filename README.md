# ConcurrentFileProcessing
Concurrent File Processing with core Java

## Used Environment

Java 1.7

Eclipse,
Version: 2018-12 (4.10.0),
Build id: 20181214-0600

JUnit 4

LibreOffice for CSV


## How to run

It is set with 4xRun Configurations and 2xJUnit Test Configurations.
Just download the full project with Eclipse and run.

## Approach

It is a simple Java Application.

Singleton design pattern for handling Concurrency Hash Maps.

Thread pool pattern design with ExecutorService and Callable task/worker for multi-thread.
Callable does not return values, in matter in fact it returns null as Void. But, as Callable is introduced to Java, Runnable is Callable that returns null. And with Callable it is easier to use tasks in collections.
Callable is updating Concurrent Hash Maps over synchronized methods from the singleton.

Files are not in memory but are scanned line by line with Scanner. It would be overkill to use Apache Commons for CSV parsing as we have strictly pre-defined and small structure of a file.

System.out is used for presenting results (as the time is running out).

## Things that can be improved   

Logger!!!
CSV parsing to be smarter and flexible with several templates.
If needs to be done as a Microservice complete redesing with Spring Batch!

## Not tested but assumed

Can support large files.

## Repository

Because of short notice, it is developed directly on Master branch. Future changes, if requested, will be commited on "Name/Task based branches", and then merged to Master (used for development).