INSERT INTO PRODUCT (productid, modelid, title, photourl, price, deliveryprice, description, quantity, details)
VALUES
    (1,'MODEL001', 'Smartphone XYZ', 'url_do_zdjecia1.jpg', 799.99, 9.99, 'Nowoczesny smartfon z wieloma funkcjami.', 100, 'Specyfikacje: ...'),
    (2,'MODEL002', 'Laptop ABC', 'url_do_zdjecia2.jpg', 1299.99, 19.99, 'Wydajny laptop idealny do pracy i rozrywki.', 50, 'Specyfikacje: ...');

INSERT INTO REVIEW (reviewid, productid, author, date, rating, content)
VALUES
    (1, 1, 'Jan Kowalski', '2022-01-01', 5, 'Świetny produkt!'),
    (2, 1, 'Anna Nowak', '2022-02-15', 4, 'Działa dobrze, ale cena mogłaby być trochę niższa.'),
    (3, 2, 'Marek Wiśniewski', '2022-03-10', 5, 'Bardzo wydajny laptop, polecam!');