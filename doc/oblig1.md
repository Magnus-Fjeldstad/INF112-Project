# Rapport – innlevering 1
**Team:** *Høytek Nightfall: Survivors Saga Delux Edition* – *Johannes Helleve, Magnus Fjeldstad, Brage Hogstad, Jens Brown Eriksen, Lyder Samnøy, Henrik Tennebekk*...

This is a test









#### A3 ####
 Prosjektmetodikken som i våre øyne egner seg best er en blanding av Scrum og Kanban, altså Scrumban. Som et team har vi avtalt å møtes minst 2 ganger i uken. Til hver "sprint" som vi vil vare 2-3 uker vil et team-medlem bli utnevt **Scrumban Master**. Scrumban Masteren vil være ansvarlig for å oppdatere Kanban-tavlen ved å ha klar og tydelig kommunikasjon med hvert eneste medlem i teamet. Både ved start og slutt på en oppgave skal Scrumban Masteren bli informert. Derav vil Scrumban Masteren kunne ha klar oversikt over hvor langt i arbeidsprosessen hvert medlem er til en hver tid.

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

3. Spilleren skal miste liv om de går i hull.

4. Spilleren kan plukke opp power ups.



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



### 1. Kai Ronny, 14 år, spiller aktivt videospill, har ikke så mange venner på skolen, faren hans lider av alkoholisme noe som gjør det litt tøft i heimen. 

1. Kai Ronny vil ha et spill som er funksjonelt.

2. Kai Ronny vil ha et spill som er underholdende og engasjerende, samt at det er en utfordring som gir han mestringsfølelse.



### 2. Sandra Borch, 35 år, nettopp gjort en tabbe på jobben og trenger en pause, hun tror videospill kan bli en god distraksjon fra virkligheten. 



### 3. Madeline, 17 år, nettopp kommet inn i en goth-fase, hun har nettopp brukt alle konfirmasjonspengene på å kjøpe en ny datamaskin og vil ha et spill som er mørkt og dystert.

