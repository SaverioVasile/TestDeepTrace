# FAQ Questionnaire App (Spring Boot + Vue)

Applicazione dimostrativa end-to-end per la prova DeepTrace:
- compilazione questionario FAQ (10 domande)
- salvataggio su PostgreSQL
- calcolo punteggio totale
- generazione report PDF
- invio report via email con provider configurabile (SMTP o AWS SES API)
- visualizzazione ultime sottomissioni (bonus)

## Struttura progetto

- `backend/` Spring Boot 3 (Java 17)
- `frontend/` Vue 3 + Vite + Tailwind
- `docker-compose.yml` deploy locale con PostgreSQL
- `docs/aws-migration.md` note per passaggio su AWS

## Prerequisiti

- Docker + Docker Compose
- (Opzionale per run locale senza container) Java 17, Maven, Node 20+

## Avvio rapido con Docker Compose (locale)

```bash
cd /Users/saverio.vasile/IdeaProjects/Test
cp .env.example .env

docker compose -f docker-compose.yml up --build
```

App disponibili su:
- Frontend: `http://localhost:5173`
- Backend API: `http://localhost:8080`

## API principali

- `POST /api/submissions`
- `GET /api/submissions`

### Payload esempio POST

```json
{
  "respondentType": "CAREGIVER",
  "respondentOther": null,
  "patientEmail": "test@example.com",
  "answers": [0, 1, 2, 3, 4, 5, 0, 1, 2, 3]
}
```

## Configurazione database (provider selezionabile)

Puoi scegliere il database con `APP_DB_PROVIDER`:
- `local` usa PostgreSQL locale/containerizzato
- `rds` usa PostgreSQL su AWS RDS

### Opzione A: locale (default)

```dotenv
APP_DB_PROVIDER=local
APP_DB_LOCAL_URL=jdbc:postgresql://postgres:5432/faqdb
APP_DB_LOCAL_USERNAME=faq
APP_DB_LOCAL_PASSWORD=faq
```

### Opzione B: AWS RDS

```dotenv
APP_DB_PROVIDER=rds
APP_DB_RDS_URL=jdbc:postgresql://<rds-endpoint>:5432/<database>
APP_DB_RDS_USERNAME=<username>
APP_DB_RDS_PASSWORD=<password>
```

Con `APP_DB_PROVIDER=rds`, il backend usa esclusivamente i parametri `APP_DB_RDS_*`.

## Configurazione email (provider selezionabile)

Per abilitare invio email reale:
1. Verifica indirizzo/domain su SES (necessario in entrambi i casi).
2. Imposta `APP_MAIL_ENABLED=true`.
3. Scegli `APP_MAIL_PROVIDER=smtp` oppure `APP_MAIL_PROVIDER=ses`.
4. Configura le variabili del provider scelto in `.env`.
5. Riavvia i container.

### Opzione A: SMTP

Usa le variabili Spring Mail:
- `SPRING_MAIL_HOST`
- `SPRING_MAIL_PORT`
- `SPRING_MAIL_USERNAME`
- `SPRING_MAIL_PASSWORD`

### Opzione B: AWS SES API

Usa le variabili SES:
- `APP_MAIL_SES_REGION`
- `APP_MAIL_SES_ACCESS_KEY`
- `APP_MAIL_SES_SECRET_KEY`

Se access key/secret key non sono valorizzate, l'app prova la AWS Default Credentials Chain.

Se `APP_MAIL_ENABLED=false`, il flusso resta dimostrabile: dati e PDF vengono generati, ma non inviati.

## Esecuzione senza Docker (opzionale)

Backend:
```bash
cd /Users/saverio.vasile/IdeaProjects/Test/backend
mvn spring-boot:run
```

Frontend:
```bash
cd /Users/saverio.vasile/IdeaProjects/Test/frontend
npm install
npm run dev
```

## Test backend

```bash
cd /Users/saverio.vasile/IdeaProjects/Test/backend
mvn test
```

## Maven dalla root (backend + frontend wrapper)

Puoi eseguire Maven dalla root del progetto (`Test/`) per lavorare a moduli:

```bash
cd /Users/saverio.vasile/IdeaProjects/Test
mvn -pl backend -am test
mvn -Pfrontend-build -pl frontend -am prepare-package
```

> Nota: il modulo `frontend` esegue `npm install` + `npm run build` solo con profilo `-Pfrontend-build`.
> Nota: il progetto include `.mvn/maven.config`, quindi Maven usa automaticamente `/.mvn-settings-personal.xml` anche da IntelliJ.

### Se npm fallisce con certificati (SELF_SIGNED_CERT_IN_CHAIN)

1. Copia il file di esempio in una configurazione locale npm del frontend:

```bash
cd /Users/saverio.vasile/IdeaProjects/Test
cp frontend/.npmrc.example frontend/.npmrc
```

2. Imposta il percorso reale del certificato CA aziendale in `frontend/.npmrc` (`cafile=...`).

3. Riprova la build del modulo frontend:

```bash
cd /Users/saverio.vasile/IdeaProjects/Test
mvn -pl frontend -am prepare-package

# con frontend profile attivo
mvn -Pfrontend-build -pl frontend -am prepare-package
```

Opzione temporanea (solo troubleshooting, non consigliata):

```bash
cd /Users/saverio.vasile/IdeaProjects/Test/frontend
npm config set strict-ssl false
```

### Se Maven fallisce con PKIX / certificate_unknown

Se sei dietro rete aziendale con TLS inspection, prepara truststore locale progetto:

```bash
cd /Users/saverio.vasile/IdeaProjects/Test
./scripts/setup-maven-truststore.sh
mvn -s /Users/saverio.vasile/IdeaProjects/Test/.mvn-settings-personal.xml test
```

Il progetto include `/.mvn/jvm.config`, quindi Maven usera automaticamente `/.certs/maven-truststore.p12`.

