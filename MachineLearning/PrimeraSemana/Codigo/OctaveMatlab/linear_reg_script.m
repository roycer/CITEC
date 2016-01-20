%function ReadAndRunLinearRegresion
clear all;
close all;
clc;
data = load('../../Datos/univariate_reg_data.txt');
X = data(:, 1); y = data(:, 2);

hold on;
X = (X - min(X)) / ( max(X) - min(X) ); % para normalizar.
size(X)
size(y)
gradient_descent_one_var(X, y, 2, 1000);
% alpha  Costo Minimo
% 0.001, 15.343802
% 0.003, 11.575697
% 0.01,  8.058022
% 0.03,  4.990743
% 0.1,   4.477538   
% 0.3,   4.476971
% 1,     4.476971
% 1.3,   4.476971
% 2,     32.072734
% 3      32.072734
% 