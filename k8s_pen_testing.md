# DIY K8s Pen Testing
* Jeff Thorne, Aqua Security

## Ports
* secure default: 443
* inecure: 8080
  * let's see if the insecure port is open?
  * `--insecure-post=0` will fix this

## User Accounts
* by default no auth requests are assigned to `system:anonymous`.
* it can be part of RBAC, so possibly could mess this up
* pods have access to the service account with their token

## Secrets
* Who can get that secret token in the Token Controller?
* kube server API and etcd api

## Kube Hunter
* skip all that `curl`ing and just automate the checks against the k8s head node