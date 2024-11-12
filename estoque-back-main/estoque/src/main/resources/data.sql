INSERT INTO Produto (id, nome, descricao, quantidade, atualizacao, validade, custo, preco, margem_lucro) VALUES 
(1, 'Pão Francês', 'Pão crocante e fresco', 3, NOW(), '2024-12-01', 0.50, 1.00, 0.50),
(2, 'Leite Integral', 'Leite integral 1L', 50, NOW(), '2024-11-10', 2.00, 4.00, 0.50),
(3, 'Arroz Branco', 'Arroz branco tipo 1', 30, NOW(), '2025-06-01', 5.00, 7.50, 0.33),
(4, 'Feijão Carioca', 'Feijão carioca 1kg', 9, NOW(), '2025-04-15', 6.00, 9.00, 0.50),
(5, 'Açúcar Refinado', 'Açúcar refinado 1kg', 0, NOW(), '2024-05-01', 2.50, 3.50, 0.28),
(6, 'Café Moído', 'Café torrado e moído 500g', 15, NOW(), '2024-12-20', 8.00, 12.00, 0.50),
(7, 'Farinha de Trigo', 'Farinha de trigo tipo 1', 7, NOW(), '2025-03-01', 4.00, 6.00, 0.33),
(8, 'Óleo de Soja', 'Óleo de soja 900ml', 4, NOW(), '2024-02-15', 5.50, 7.00, 0.21),
(9, 'Macarrão Espaguete', 'Macarrão espaguete 500g', 50, NOW(), '2025-08-01', 3.00, 5.00, 0.40),
(10, 'Manteiga', 'Manteiga com sal 200g', 1, NOW(), '2024-11-25', 6.50, 10.00, 0.54),
(11, 'Queijo Mussarela', 'Queijo mussarela fatiado', 30, NOW(), '2024-12-05', 18.00, 25.00, 0.39),
(12, 'Presunto', 'Presunto fatiado 200g', 6, NOW(), '2024-11-30', 10.00, 15.00, 0.50),
(13, 'Tomate', 'Tomate fresco 1kg', 2, NOW(), '2024-11-12', 4.50, 8.00, 0.44),
(14, 'Alface', 'Alface americana unidade', 1, NOW(), '2024-11-09', 1.00, 2.50, 0.60),
(15, 'Cenoura', 'Cenoura fresca 1kg', 35, NOW(), '2024-11-15', 3.50, 6.00, 0.42),
(16, 'Banana', 'Banana prata 1kg', 9, NOW(), '2024-11-10', 2.00, 4.50, 0.56),
(17, 'Maçã', 'Maçã gala 1kg', 5, NOW(), '2024-10-12', 4.00, 6.50, 0.38),
(18, 'Batata', 'Batata inglesa 1kg', 55, NOW(), '2025-01-01', 3.00, 5.00, 0.40),
(19, 'Cebola', 'Cebola branca 1kg', 0, NOW(), '2025-01-10', 3.50, 5.50, 0.36),
(20, 'Alho', 'Alho descascado 200g', 2, NOW(), '2024-02-20', 12.00, 18.00, 0.50),
(21, 'Frango Congelado', 'Frango inteiro congelado', 4, NOW(), '2024-12-01', 15.00, 25.00, 0.66),
(22, 'Carne Moída', 'Carne bovina moída 1kg', 0, NOW(), '2024-11-18', 20.00, 30.00, 0.50),
(23, 'Peito de Frango', 'Peito de frango 1kg', 10, NOW(), '2024-11-01', 10.00, 16.00, 0.60),
(24, 'Salsicha', 'Salsicha para hot dog 500g', 0, NOW(), '2024-12-05', 5.00, 8.00, 0.38),
(25, 'Refrigerante', 'Refrigerante cola 2L', 30, NOW(), '2025-04-15', 6.00, 10.00, 0.40),
(26, 'Suco de Laranja', 'Suco natural 1L', 6, NOW(), '2024-11-25', 5.00, 9.00, 0.44),
(27, 'Cerveja', 'Cerveja pilsen lata 350ml', 10, NOW(), '2025-06-01', 2.50, 5.00, 0.50),
(28, 'Vinho Tinto', 'Vinho tinto seco 750ml', 8, NOW(), '2026-12-01', 25.00, 45.00, 0.44),
(29, 'Detergente', 'Detergente líquido 500ml', 3, NOW(), '2025-10-01', 1.50, 3.00, 0.50),
(30, 'Sabonete', 'Sabonete glicerinado', 100, NOW(), '2026-01-01', 2.00, 4.00, 0.50);



INSERT INTO Movimentacao (id, produto_id, nome_produto, quantidade_alterada, data_movimentacao, tipo_movimentacao) VALUES
(1, 1, 'Pão Francês', 5, NOW(), 'ENTRADA'),
(2, 2, 'Leite Integral', 3, NOW(), 'SAIDA'),
(3, 3, 'Arroz Branco', 10, NOW(), 'ENTRADA'),
(4, 4, 'Feijão Carioca', 2, NOW(), 'SAIDA'),
(5, 5, 'Açúcar Refinado', 7, NOW(), 'ENTRADA'),
(6, 6, 'Café Moído', 5, NOW(), 'SAIDA'),
(7, 7, 'Farinha de Trigo', 4, NOW(), 'ENTRADA'),
(8, 8, 'Óleo de Soja', 1, NOW(), 'SAIDA'),
(9, 9, 'Macarrão Espaguete', 12, NOW(), 'ENTRADA'),
(10, 10, 'Manteiga', 3, NOW(), 'SAIDA'),
(11, 11, 'Queijo Mussarela', 6, NOW(), 'ENTRADA'),
(12, 12, 'Presunto', 4, NOW(), 'SAIDA'),
(13, 13, 'Tomate', 8, NOW(), 'ENTRADA'),
(14, 14, 'Alface', 2, NOW(), 'SAIDA'),
(15, 15, 'Cenoura', 10, NOW(), 'ENTRADA'),
(16, 16, 'Banana', 6, NOW(), 'SAIDA'),
(17, 17, 'Maçã', 3, NOW(), 'ENTRADA'),
(18, 18, 'Batata', 2, NOW(), 'SAIDA'),
(19, 19, 'Cebola', 9, NOW(), 'ENTRADA'),
(20, 20, 'Alho', 3, NOW(), 'SAIDA');




INSERT INTO Fornecedor (id, nome, email, telefone, endereco) VALUES 
(1, 'Supermercado Central', 'contato@supercentral.com', '(11) 98765-4321', 'Av. Paulista, 1000, São Paulo - SP'),
(2, 'Distribuidora de Alimentos Silva', 'vendas@distsilva.com', '(21) 99874-1234', 'Rua das Laranjeiras, 456, Rio de Janeiro - RJ'),
(3, 'Comercial Alimentos Brasil', 'info@alimentosbr.com', '(31) 91234-5678', 'Av. Afonso Pena, 1200, Belo Horizonte - MG'),
(4, 'Casa do Café', 'sac@casadocafe.com.br', '(41) 98765-4321', 'Rua XV de Novembro, 234, Curitiba - PR'),
(5, 'Fazenda Orgânicos', 'contato@fazendaorganicos.com', '(51) 99876-5432', 'Estrada do Campo, 789, Porto Alegre - RS'),
(6, 'Doces & Cia', 'contato@docesecia.com', '(61) 91234-8765', 'SQN 208, Bloco D, Brasília - DF'),
(7, 'Frutas Tropicais', 'vendas@frutastropicais.com', '(27) 99887-6543', 'Av. Vitória, 300, Vitória - ES'),
(8, 'Laticínios da Serra', 'sac@laticiniosserra.com.br', '(62) 98765-9876', 'Rua Goiás, 56, Goiânia - GO'),
(9, 'Cervejaria Artesanal', 'contato@cervejaartesanal.com', '(81) 91234-4321', 'Rua da Aurora, 89, Recife - PE'),
(10, 'Hortifruti Qualidade', 'contato@hortifrutiqualidade.com', '(71) 99876-1234', 'Av. Paralela, 1500, Salvador - BA');

















