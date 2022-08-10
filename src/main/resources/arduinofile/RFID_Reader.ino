#include <SPI.h>
#include <MFRC522.h>

// контакты
#define SS_PIN 10
#define RST_PIN 9
#define LED_R A2
#define LED_G A0
#define LED_GND A1
#define BUZZER 7
#define BUZZER_GND 5

// Создание экземпляра объекта MFRC522
MFRC522 mfrc522(SS_PIN, RST_PIN);  // Создание экземпляра MFRC522

void setup() {
  Serial.begin(9600);
  SPI.begin();

  // инициализация MFRC522
  mfrc522.PCD_Init();
  // выводим номер версии прошивки ридера
  //mfrc522.PCD_DumpVersionToSerial();

  pinMode(LED_G, OUTPUT);
  pinMode(LED_R, OUTPUT);
  pinMode(LED_GND, OUTPUT);
  pinMode(BUZZER, OUTPUT);
  pinMode(BUZZER_GND, OUTPUT);

  digitalWrite(LED_G, HIGH);
  digitalWrite(LED_R, HIGH);
  digitalWrite(BUZZER, HIGH);
  delay(100);
  digitalWrite(BUZZER, LOW);
  digitalWrite(LED_G, LOW);
  digitalWrite(LED_R, LOW);
}

void loop() {
  // Ожидание
  if ( ! mfrc522.PICC_IsNewCardPresent())
    return;

  // чтение
  if ( !mfrc522.PICC_ReadCardSerial())
    return;

  // вывод данных
  Serial.print(".UID.");
  view_data(mfrc522.uid.uidByte, mfrc522.uid.size);
  Serial.print(".END.");
  Serial.println();

delay(100);

  while (Serial.available()) {

    char val = Serial.read();
    if (val == '1') {
      digitalWrite(LED_G, HIGH);
      digitalWrite(BUZZER, HIGH);
      delay(100);
      digitalWrite(BUZZER, LOW);
      delay(50);
      digitalWrite(BUZZER, HIGH);
      delay(100);
      digitalWrite(BUZZER, LOW);
    }
    if (val == 'C') {
      digitalWrite(LED_R, HIGH);
      digitalWrite(BUZZER, HIGH);
      delay(250);
      digitalWrite(BUZZER, LOW);
    }
  }
  
  delay(200);
  digitalWrite(LED_G, LOW);
  digitalWrite(LED_R, LOW);
}

// преобразование в HEX
void view_data (byte *buf, byte size) {
  for (byte j = 0; j < size; j++) {
    //Serial.print(buf [j]);
    Serial.print(buf [j], HEX);
  }
}
