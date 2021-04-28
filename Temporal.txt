#include <iostream>
#include "LibreriaColas.h"
using namespace std;
int main(int argc, char** argv) {
	Cola insertar;
	insertar.insercion(4);
	insertar.insercion(5);
	insertar.eliminacion();
	insertar.eliminacion();
	insertar.insercion(5);
	insertar.insercion(519);
	insertar.insercion(412);
	insertar.insercion(421);
	return 0;
}
