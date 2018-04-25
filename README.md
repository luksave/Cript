# E-mail criptografado
Usando criptografia no envio de um e-mail.

### Padrão
* Linguagem: Java
* Gerador de chaves: classe KeyGenerator
* Função de hash: MD5
* Algoritmo de criptografia: AES
* Servidor de integração contínua: Travis-CI

## O que fazer
- Implementar uso do g-mail através da aplicação.
- Gerar chaves pública e privada. Um par para o Remetente e um para o destinatário
- Publicar as chaves públicas em algum lugar.


### **Gerar Arquivo - Remetente**
1. Criptografar uma mensagem usando AES. Para isso, precisamos de uma chave simétrica qualquer gerada na hora da criptografia.
2. Calcular o hash MD5 da mensagem ainda não criptografada. Para isso, use a chave privada do remetente. Isso gera uma hash criptografada da mensagem. *USAR RSA.*
3. Aplicar a chave pública do destinatário na chave escolhida para o AES, gerando assim a chave criptografada do AES.
4. Anexar esses três arquivos gerados e mandar como e-mail para o destinatário.


### **Ler Arquivo - Destinatário**
1. Usar a chave pública do remetente para calcular o hash MD5 da mensagem.
2. Usar a chave privada do destinatário para descriptografar a chave usada no AES.
3. Usar a chave descriptografa do AES para descriptografar a mensagem do e-mail.
4. Calcular o Hash MD5 da mensagem obtida em 3 usando a chave pública do remetente.


##### *PROBLEMA:* Como encontrar as chaves públicas do remetente e do destinatário?
1. Trocar as chaves antes de enviar o e-mail com arquivos criptografados; ou
2. Criar um servidor de chave pública e privada e consultá-lo para obter as chaves; ou
3. Apenas publicar as chaves em algum lugar qualquer como o google drive.
