# Steaming Services
* Benjamin Picolo

## GRPC Streaming
* Python, Typescript, gRPC and Protobuf, ZeroMQ
* @improbably-eng/grpc-web - a more stable web library for front end.
  * there is an offical one but it isn't as good.

## Create a Service
```
syntax = "proto3"

service TinyKafka {
  rpc SendMessage(SendMessageRequest) returns (SendMessageResponse);

  rpc Watch(WatchRequest) returns (stream WatchResponse);
}

message SendMessageRequest {
  string message = 1;
}
```

* A bunch of python he already wrote...
* He created a server in python and typescript in about 30 minutes
* Templated React app for a hello world with streaming