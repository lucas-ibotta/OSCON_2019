# Secrets in Serverless
* Seth Vargo
* https://github.com/sethvargo/secrets-in-serverless

## How do we authenticate?
* Redis needs a password.
* Within VPN? Use IAM?
* Don't use environment variables for secrets! It gets dumped into debugging messages.
* https://github.com/sethvargo/go-malice. Software supply chain attack. Second reason not to use ENV variables.

* Encrypt it at storage and then store it in the ENV variable. It would only be in the core dump of the machine's memory.
* berglas to encrypt key with KMS
