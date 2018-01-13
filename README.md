# SudokuEbatzaile
### Deskribapena
Sudokuak ebazteko aplikazio erraz bat. 

**.csv** fitxategi batetik irakurtzen dira Sudokuaren hasierako datuak. Goitik behera eta ezkerretik eskuinera irakurtzen dira datuak fitxategitik eta separadore moduan puntu eta koma ( **;** ) erabiltzen da. Hainbat adibide ematen dira */res* karpetan.

Adibidez, eduki hau daukan fitxategiak...

```
8;;;;;;;;;;;3;6;;;;;;;7;;;9;;2;;;;5;;;;7;;;;;;;;4;5;7;;;;;;1;;;;3;;;;1;;;;;6;8;;;8;5;;;;1;;;9;;;;;4;;;
```

... sudoku hau sortuko luke:
```
+---------+---------+---------+
| 8  .  . | .  .  . | .  .  . |
| .  .  3 | 6  .  . | .  .  . |
| .  7  . | .  9  . | 2  .  . |
+---------+---------+---------+
| .  5  . | .  .  7 | .  .  . |
| .  .  . | .  4  5 | 7  .  . |
| .  .  . | 1  .  . | .  3  . |
+---------+---------+---------+
| .  .  1 | .  .  . | .  6  8 |
| .  .  8 | 5  .  . | .  1  . |
| .  9  . | .  .  . | 4  .  . |
+---------+---------+---------+
```

Adibideak hemendik hartu dira:
* [University of Arizona](http://elmo.sbs.arizona.edu/sandiway/sudoku/examples.html)
* [WebSudoku](http://www.websudoku.com)

### Lizentzia
GNU General Public License v3.0. ([lizentziaren testua](https://www.gnu.org/licenses/gpl-3.0.en.html))
