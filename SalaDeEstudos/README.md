## Projeto de implementação de Tabela Hash

A classe "SalaDeEstudos", como o nome sugere, trata-se de um sistema de reserva de sala para estudantes de um determinado cólegio. A partir dela, foi implementada a classe "TabelaHash", que realiza a mesma função, porém, utilizando a lógica de uma Tabela Hash com endereçamento aberto.

# Clase: SalaDeEstudos

Iniciamos tratando os horários de reserva como posições de um array, existindo ao todo 6 possibilidades de horário para realizar uma reserva. 

Para realizar uma reserva o aluno informa seu nome e o horário que quer reservar (o horário se dá em relação à posição do elemento no array, isto é, se ele quiser reservar o primeiro horário, 7:00h, ele devera informar '0', o horário seguinte, 8:40h, corresponde ao número '1' e, assim, sucessivamente).

Caso o horário esteja disponível o aluno poderá utilizar a sala naquele horário, caso esteja ocupado, ele ficará numa fila de espera, aguardando caso o aluno que se inscreveu para usar a sala naquele horário primeiro desista.

Além disso, todas as ações ficam salvas num histórico que é impresso no final, a fim de manter o controle de todas as atividades que foram realizadas.

# Classe: HashingOpenAdd

// Atualizando.
