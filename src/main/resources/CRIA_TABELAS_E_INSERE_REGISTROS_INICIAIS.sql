CREATE TABLE exame (rowid bigint auto_increment PRIMARY KEY, nm_exame VARCHAR(255));
INSERT INTO exame (nm_exame) VALUES ('Acuidade Visual'), ('Urina'), ('Clinico'), ('Sangue');
CREATE TABLE funcionario (rowid bigint auto_increment PRIMARY KEY, nm_funcionario VARCHAR(255));
INSERT INTO funcionario (nm_funcionario) VALUES ('Gyro Zeppeli'), ('Francklin Júnior'), ('João Santos'), ('Paula Fernandes');
CREATE TABLE funcionario_exame (
    exame_id BIGINT,
    funcionario_id BIGINT,
    data_exame VARCHAR(255),
    rowid BIGINT auto_increment,
    CONSTRAINT fk_exame FOREIGN KEY (exame_id) REFERENCES exame(rowid),
    CONSTRAINT fk_funcionario FOREIGN KEY (funcionario_id) REFERENCES funcionario(rowid) ON DELETE CASCADE,
    PRIMARY KEY (exame_id, funcionario_id, data_exame)
);

INSERT INTO funcionario_exame (exame_id, funcionario_id, data_exame) VALUES (1, 2, '2023-10-20');
INSERT INTO funcionario_exame (exame_id, funcionario_id, data_exame) VALUES (1, 3, '2023-10-20');
INSERT INTO funcionario_exame (exame_id, funcionario_id, data_exame) VALUES (2, 1, '2023-10-21');
INSERT INTO funcionario_exame (exame_id, funcionario_id, data_exame) VALUES (3, 4, '2023-10-22');

