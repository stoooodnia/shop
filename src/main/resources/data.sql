INSERT INTO Product (modelId, title, photoUrl, price, deliveryPrice, description, quantity, details)
VALUES
  ('MODEL001', 'Smartphone XYZ', 'url_do_zdjecia1.jpg', 799.99, 9.99, 'Nowoczesny smartfon z wieloma funkcjami.', 100, 'Specyfikacje: ...'),
  ('MODEL002', 'Laptop ABC', 'url_do_zdjecia2.jpg', 1299.99, 19.99, 'Wydajny laptop idealny do pracy i rozrywki.', 50, 'Specyfikacje: ...'),
INSERT INTO Review (productId, author, date, rating, content)
VALUES
  (1, 'Jan Kowalski', '2022-01-01', 5, 'Świetny produkt!'),
  (1, 'Anna Nowak', '2022-02-15', 4, 'Działa dobrze, ale cena mogłaby być trochę niższa.'),
  (2, 'Marek Wiśniewski', '2022-03-10', 5, 'Bardzo wydajny laptop, polecam!'),

