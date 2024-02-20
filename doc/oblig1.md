# Rapport – innlevering 1
**Team:** *Høytek Nightfall: Survivors Saga Delux Edition* – *Johannes Helleve, Magnus Fjeldstad, Brage Hogstad, Jens Brown Eriksen, Lyder Samnøy, Henrik Tennebekk*...


### A1:

Se på README.md

### A2: Konsept
I Høytek Nightfall: Survivors Saga styrer spilleren en 2D karakter i form av en "sprite". Spillverden er top-down, og spillkarakteren kan dermed bevege seg i alle retninger med wasd eller piltastene. Spillverden vil ta utgangspunkt i en cellegrid, der hver celle er enten navigerbar eller en hindring.

Spillet skal etterligne en "roguelite" som f.eks. Vampire Survivors, Death Must Die, Risk of Rain 2, The Binding of Isaac, Dead Cells, Hades, Noita, Slay the Spire etc. (for å nevne de jeg anbefaler), men aller mest tar vi utgangspunkt i Vampire Survivors.

Dette medfølger at:
En har flere levler og karakterer å velge mellom (vi sikter på to av begge, mest for å vise implementering).
Hvert forsøk på et level omtales som et "run", og en timer (altså klokke) tikker opp til runnet er ferdig (ofte 20-30 min, vi sikter på i underkant av 10 min).
Mens runnet pågår blir en angrepet av stadig flere og vanskeligere fiender, disse fiendene beverger seg mot spillkarakteren og gjør skade ved kontakt.
Dersom spillkarakteren dør blir en sendt tilbake til startmenyen og runnet er over. Forhåpentligvis har spilleren samlet noen tokens (power-ups i levelet). Tokens gjør det mulig å kjøpe permanente stat-oppgraderinger mellom runs, slik at du er sterkere enn du var i forrige forsøk, og dermed kommer lengre i ditt neste run.

De to spillkarakterene skal ha forskjellige våpen for å gi spilleren flere muligheter. Vi tenker å ha en som kan skyte en ildkule i en rett linje, og en som utfører et kjegleformet angrep med f.eks. et sverd. Disse våpnene skal kunne oppgraderes i løpet av runnet for å bekjempe de stadig farligere fiendene (få, men betydelige oppgraderinger som endrer hvordan våpenet fungerer, flere ildkuler etc.)

Den siste funksjonen vi ønsker å implementere er et "skilltre". Spilleren kan samle "skillpoints" via å bekjempe ekstra tøffe fiender som dukker opp med jevne intervaller. Disse skillpoints'ene skal brukes til å gi bedre player-stats som mer damage eller HP, men varer bare ut runnet, og blir dermed resettet etter hvert run. Hensikten her er at spilleren får færre skillpoints en det er mulig å velge i skilltreet, så spilleren må bestemme hvilke stats som passer best i situasjonen, og hvilke av de respektive grenene spilleren ønsker å fylle ut (noder på grenene som er lengst unna gir mer betydelige bonuser). Vi håper at skilltreet vil gi en følelse av at spillkarakteren er relativt svak i starten av en run, men kan bygge seg opp til å bli mye strekere for å matche spillets økende vanskelighetsgrad ettersom tilden øker.

Elementet som mangler i vår versjon er følelsen av "randomness" der du f.eks. kan få vidt forskjellige våpen eller oppgraderinger underveis som sender runnet i en overraskende retning, og dermed gjør at du kan/må bygge karakteren din på en måte du ikke forutså. Dette gir den effekten at to runs kan oppleves helt forskjellig selv om utgangspunktet var det samme, noe som fører til det magiske konseptet "replayability". Vi håper å finne en løsning på dette som ikke krever mye mer tid enn er tilgjengelig.

...


### A3: 
 Prosjektmetodikken som i våre øyne egner seg best er en blanding av Scrum og Kanban, altså Scrumban. Som et team har vi avtalt å møtes minst 2 ganger i uken. Til hver "sprint" som vi vil vare 1-2 uker vil et team-medlem bli utnevt **Scrumban Master**. Scrumban Masteren vil være ansvarlig for å oppdatere Kanban-tavlen ved å ha klar og tydelig kommunikasjon med hvert eneste medlem i teamet. Både ved start og slutt på en oppgave skal Scrumban Masteren bli informert. Derav vil Scrumban Masteren kunne ha klar oversikt over hvor langt i arbeidsprosessen hvert medlem er til en hver tid.

Det er flere ting som er viktige å tenke på ved bruken av Scrumban. Først å fremst må lengden på en sprint være i samsvar med arbeidsmengden. På toppen av dette igjen er det lurt å i tillegg prioritere rekkefølge på oppgaver i tilfelle problemer oppstår. Dersom man jobber med arbeidoppgaver utenfor sprintens plan vil det være meningsløst å benytte seg av prosjektmetodikken. Det er derfor viktig at alle overholder sine plikter i forhold til arbeidsoppgaver og tydelig dokumentasjon.

For uten om fysiske møter vil det være pågående kommunikasjon på både Discord og Messenger. Den viktigste form for kommunikasjon vil bli mellom team-medlem og Scrumban Master. I og med at Scrumban Master kommer til å ha ansvar for både oversikt samt en egen del av arbeidsmengden vil Scrumban Master bli tildelt litt mindre arbeidsoppgaver.

# A3: Få en oversikt over forventet produkt

## Beskrivelse av det overordnede målet til produktet 

Det overordnede målet til produktet er å lage et spill som er underholdende og engasjerende for spilleren. 

## Minimum Viable Product (MVP)

1. Spilleren får presentert en forside med en stor overskrift og musikk spilles for å skape en stemning. Samtidig finnes det en knapp for instruksjoner og å starte spillet. 

2. Når spilleren trykker på spill viser skjermen et spillbrett hvor spillerens karakater står i midten.

3. Spilleren kan flytte karakteren ved hjelp av tastaturet.

4. Spilleren kan bevege seg med terrenget som har effekter på karakteren.

5. Fiender blir konstruert utenfor skjermen og beveger seg mot spilleren.

6. Spilleren kan angripe fiender og fiender kan angripe spilleren.

7. Spilleren kan plukke opp power ups som gir spilleren en kortvarig fordel.

7. Spilleren får poeng for å drepe fiender.

8. Spilleren har en gitt mengde liv og mister liv når fiender angriper/kommer i kontakt med spilleren.

9. Etter ett antall fiender er drept skal spilleren få en meny som viser ulike oppgraderinger spilleren kan velge mellom.

10. Når spilleren mister alle livene sine, vises en skjerm som viser at spillet er over og en mulighet for å starte spillet på nytt.


## Brukerhistorier

### Brukerhistorie 1: Lage terreng 

Som spiller ønsker jeg å forstå strukturen av terrenget som vegger/hull/power ups og å mulighet til å bevege meg. Samtidig ønsker jeg som programmerer å lage en funksjonalitet som gjør at spillerns karakter reagerer på ulike elementer i terrenget. Karakteren skal være uavhengig av terrenget og ikke være en del av brettet, men ved hjelp av kollisjon skal karakteren kunne reagere på terrenget.

#### Akseptansekriterier

1. Spilleren kan bevege seg rundt på skjermen ved hjelp av tastaturet.

2. Spilleren kan ikke gå gjennom vegger.

3. Spilleren kan plukke opp power ups.



### Brukerhistorie 2: Fiender

Som spiller ønsker jeg å kunne se fiender på skjermen og angripe de, fiendene skal gå mot meg. Som programmerer ønsker jeg å lage en funksjonalitet som gjør at fiender blir konstruert utenfor skjermen og beveger seg mot spilleren. Fienden skal ikke være en del av brettet. Samtidig ønsker jeg å holde en oversikt over poeng for fiender som blir drept, liv for spilleren og at fiender kan angripe. Det skal også være mulig å lage ulike typer fiender.

#### Akseptansekriterier

1. Spilleren kan se fiender på skjermen.

2. Fiender beveger seg mot spilleren.

3. Spilleren kan angripe fiender.

4. Fiender kan angripe spilleren.

5. Spilleren får poeng for å drepe fiender.

6. Spilleren mister liv når fiender angriper/kommer i kontakt med spilleren.

7. Det skal være mulig å lage ulike typer fiender.



### Brukerhistorie 3: Oppgraderinger

Som spiller ønsker jeg å kunne velge mellom ulike permanente oppgraderinger etter en gitt mengde fiender er drept. Spilleren skal også få forbedret egenskaper av å plukke opp forskjellige power ups og hvor lenge den varer. Som programmerer ønsker jeg å holde oversikt over karakterens egenskaper og hva som skjer når en spiller velger en oppgradering, samtidig hva som skjer når en spiller plukker opp en power up og varigheten av den.

#### Akseptansekriterier

1. Spilleren får en meny som viser ulike oppgraderinger spilleren kan velge mellom som har permanete forbedring på egenskapene til spilleren.

2. Spilleren får forbedret egenskaper av å plukke opp forskjellige power ups og hvor lenge den varer.


### Brukerhistorie 4: Spillet er over

Som spiller ønsker jeg å få en sluttskjerm som spør om jeg vil starte spillet på nytt og poengsum. Som programmerer ønsker jeg å ha en knapp som restarter spillet og viser poengsummen til spilleren for denne runden.


#### Akseptansekriterier

1. Spilleren får en skjerm som viser at spillet er over og en mulighet for å starte spillet på nytt.

2. Spilleren får poengsummen for denne runden.


### A4
Laget et simpelt skjellet av et spill. Man kan flytte på den svarte sirkelen ved bruk av piltastene. Kameraet følger "spilleren" og mappet blir renderet ved bruk av libgdx sin OrthoganolTiledMapRenderer. Mappet er laget i Tiled.

### A5 
Oppsummering av Prosjekt: Høytek Nightfall: Survivors Saga Deluxe Edition
I løpet av de innledende fasene av utviklingen av "Høytek Nightfall: Survivors Saga Deluxe Edition", har teamet vårt gjennomført en rekke planleggings- og organisasjonsaktiviteter for å legge et solid fundament for prosjektet. Til tross for at vi er i en tidlig fase og ikke har begynt kodingsarbeidet ennå, har vi oppnådd betydelige fremskritt i å forme prosjektets retning og struktur.

Hva Gikk Bra:

• Teamorganisering: Vi har effektivt fordelt roller innen teamet, noe som har sikret at alle medlemmer har klare ansvarsområder. Dette har bidratt til å fremme en følelse av eierskap og ansvarlighet blant teammedlemmene.
• Konseptutvikling: Klarheten i spillkonseptet og den generelle entusiasmen rundt spillideen har vært høy. Dette har lagt grunnlaget for en felles visjon for spillet, noe som er avgjørende for motivasjonen og samarbeidet videre i prosjektet.
• Kommunikasjon: Bruken av Discord som vår hoved kommunikasjonskanal har vist seg å være effektiv for å opprettholde jevn og åpen dialog blant teammedlemmene. VI har i tillegg laget en chat på Messenger, der vi planlegger møter, og sier i fra dersom man ikke kan møte, eller kommer for seint. Dette ble lagd, ettersom vi syntes slik informasjon egner seg bedre for en annen plattform enn discord.

Hva har vært vanskelig:

Git: Vi har ønsket å sette en god struktur for hvordan vi som gruppe skal bruke Gitlab. Vi ser på dette som en veldig verdifull plattform, som er nødvendig for alle og en hver å sette seg dypt inn i. Ved så sette en god struktur og skape forståelse av denne plattform tidlig i prosessen, vil det kunne forhindre mange framtidig problemer, og skape et bedre samarbeid. Vi har prøvd oss mye fram og tilbake for å forstå hvordan vi skal bruke dette verktøyet, og føler etter mye tid at vi har alle fått en dypere forståelse av Git. 