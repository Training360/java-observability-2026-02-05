# Spring Boot Observability

Attól, hogy valami működik, még nem biztos, hogy elég. Az Observability témaköre a Spring Boot dokumentációjában is a Production-ready Features alatt szerepel. 
Sok fogalom keveredik itt, Actuator, metrics, tracing, log, Micrometer, OpenTelemetry, OLTP. Előadásomban ezeket szeretném kicsit rendbe tenni. Persze pont a 
szabványok miatt sok stack közül lehet választani, a gyakorlati részben az OpenTelemetry LGTM-et fogom bemutatni, mely magába foglalja az OpenTelemetry Collector, 
Grafana, Loki, Mimir, és Tempo komponenseket. Ha nem ezt használjuk, akkor is érdemes megnézni, hiszen más stack esetén is hasonlóak a koncepció, felépítés és fogalmak.

Persze ezekkel az eszközökkel könnyebb megtalálni a hibát, de vannak olyan eszközök, melyek az automatikus hibajavításra is képesek. A Spring Framework 7 egyik újítása az ún. 
Resilience Features, ami gyakorlatilag a Retry, azaz újrapróbálkozás mintát valósítja meg. Ennek működését is megvizsgáljuk Spring Boot alkalmazáson belül.

