# E-mail criptografado
Usando criptografia no envio de um e-mail.

### Padrão
* Linguagem: Java
* Arquitetura: Cliente-Servidor
* Gerador de chaves: classe KeyGenerator
* Função de hash: MD5
* Algoritmo de criptografia: AES 

## Enunciado
Implementar um protótipo de um software que permita a troca de e-mails seguros em serviços públicos
de troca de e-mails (como o Gmail, por exemplo). Esse protótipo deverá gerar os três arquivos que serão
enviados nos e-mails. O envio e recebimento dos e-mails serão feitos usando a interface específica do
serviço utilizado (por exemplo, a interface Web do Gmail), onde serão anexados esse três arquivos no email
enviado. O envio e recebimento de e-mails deverão funcionar de acordo com as características
descritas abaixo.

## ENVIAR UM E-MAIL
1. Se o usuário corrente (remetente da mensagem) não tiver um par de chaves pública-privada
(criptografia assimétrica), o software deverá gerar essas chaves e disponibilizar a chave pública
para que os destinatários dos e-mails possam baixa-la; (FEITO)

2. As mensagens enviadas poderão ter, além dos textos das mensagens, arquivos anexados, sem
restrição ao tipo e tamanho desses arquivos; (OK)

3. Uma vez escrito o texto e escolhido os arquivos anexados (casos existam anexos), o software
deverá calcular um hashing da mensagem e arquivos de forma a garantir que o destinatário
valide a integridade da mensagem caso o e-mail seja alterado durante a transmissão;

4. Usando a chave privada do usuário corrente, o software deverá criptografar o hashing calculado
na etapa anterior e armazenar o hashing criptografado em um arquivo (hash.arq);

5. O software deverá gerar uma chave de criptografia simétrica (cada e-mail enviado terá uma
chave diferente), com tamanho mínimo suficiente para tornar a criptografia segura (à prova de
quebra por força bruta). Essa chave será usada para criptografar o texto e arquivos anexos da
mensagem que será enviada; (FEITO)

6. Criptografar o texto e anexos da mensagem usando o algoritmo AES (criptografia simétrica) com
a chave gerada na etapa 5, e armazenar esse conteúdo criptografado em um arquivo (msg.arq) (FEITO)

7. Utilizando a chave pública do destinatário, criptografar a chave gerada na etapa 5 e armazenar
essa chave criptografada em um arquivo (key.arq). O software deverá fazer o download dessa
chave a partir de um local seguro, conforme definido na etapa 1. [Obs.: caso o usuário envie essa
mensagem para diversos destinatários, cada destinatário receberá um arquivo criptografado
com sua chave pública específica, ou seja, serão gerados arquivos key.arq distintos para cada
destinatário];

8. Usando o serviço de envio de mensagens (por exemplo, o Gmail), criar um novo e-mail para o
destinatário e anexar os arquivos gerados anteriormente (hash.arq, msg.arq e key.arq); (SIMPLES)

9. Enviar o e-mail. (SIMPLES)

## LER UM E-MAIL
1. Usando a chave pública do remetente da mensagem, decriptografar o arquivo que contém o
hashing (hash.arq). Como estamos utilizando o par de chaves pública-privada do remetente,
temos a garantia de identidade de quem enviou o e-mail; (FEITO +-)

2. Usando a chave privada do usuário corrente (destinatário da mensagem), decriptografar o
arquivo que contém a chave simétrica usada para criptografar o texto e arquivos anexos da
mensagem original (key.arq). Como estamos utilizando o par de chaves pública-privada do
destinatário, temos a garantia de que o e-mail recebido foi enviado para o usuário corrente;

3. Usando a chave obtida na etapa 2, decriptografar a mensagem original e seus arquivos anexados
(msg.arq) usando o algoritmo de criptografia AES; (FEITO)

4. Calcular o hashing da mensagem e arquivos obtidos na etapa 3 e comparar com o hashing obtido
na etapa 1. Se os hashings forem diferentes, a mensagem recebida foi alterada durante a
transmissão; (FEITO +-)


## ENTREGA DO PROJETO
Esse trabalho poderá ser feito em grupo de, no máximo 4 (quatro) alunos, até o dia 30/junho/2018.
Deverá ser entregue no SIGAA:
* Um arquivo ZIP contendo o código fonte do protótipo;
* Um arquivo PDF contendo cópias das telas durante a execução do protótipo, ou seja, geração
dos arquivos e envio/recebimento dos e-mails, comprovando que o protótipo funciona
* Somente um aluno do grupo deverá enviar o trabalho, não esquecendo de colocar os nomes dos
componentes do grupo no arquivo PDF.
