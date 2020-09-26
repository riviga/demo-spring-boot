# language: es
Característica: Actualizar usuario

  Escenario: actualizar nombre de usuario
    Dado un usuario existente con id 1 y nombre Juan
    Cuando cambiamos el nombre de este usuario por John
    Entonces tras consultarlo otra vez, su nombre debe ser John
