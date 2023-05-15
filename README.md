# Nombre del Proyecto

El proyecto se ha dividido en 4 módulos para facilitar la escalabilidad. A continuación se describen los módulos y sus funcionalidades principales:

## Core

El módulo "Core" contiene las clases y funciones principales del proyecto. Este módulo tiene dependencias con la mayoría de los otros módulos y proporciona la lógica central del proyecto.

## Common

El módulo "Common" contiene interfaces comunes que son utilizadas por varios módulos. Estas interfaces facilitan la comunicación y la integración entre los diferentes componentes del proyecto.

## Integrations

El módulo "Integrations" incluye una integración realizada con Feign. Esta integración se ha desarrollado para conectar con el módulo "Currencies", que es un módulo independiente con sus propias dependencias.

## Currencies

El módulo "Currencies" se centra en la gestión de divisas. Contiene la lógica y las funciones relacionadas con las operaciones de divisas. Este módulo ha sido diseñado de forma aislada, con dependencias independientes y se puede utilizar de manera independiente o integrado con otros módulos según sea necesario.

## Instrucciones de Arranque

Sigue los pasos a continuación para ejecutar el proyecto:

1. Inicializa el archivo `docker-compose.yml` ubicado en la carpeta raíz del proyecto ejecutando el siguiente comando:

   ```bash
   docker-compose up -d
Esto iniciará los servicios y contenedores necesarios para el proyecto.

2. Instala las dependencias del proyecto utilizando Maven. Ejecuta el siguiente comando en la carpeta raíz del proyecto:
    ```
   mvn install
Esto descargará y configurará todas las dependencias necesarias para los módulos.

3. Inicia el módulo **Currencies** ejecutando la clase CurrenciesApp. Este módulo se encarga de establecer el servidor WireMock y configurar los stubs para las llamadas de la API simulada.

4. Finalmente, inicia el módulo **Core** ejecutando la clase RateServiceApp. Este módulo utilizará las dependencias de los otros módulos y ejecutará la lógica principal del proyecto.

Asegúrate de que todos los servicios y dependencias estén correctamente configurados y funcionando antes de ejecutar los módulos individuales.
