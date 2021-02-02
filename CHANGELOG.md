# QA Catalogue Changelog

## [Unreleased issue-75-british-library]

### Added

- British Library tags: 039, 091, 509, 539, 590, 591, 592, 594, 595, 596, 597, 598, 599,
  690, 692, 852$a (code list), 859, 909, 916, 917, 945, 950, 954, 955, 957, 959, 960,
  961, 962, 963, 964, 966, 968, 970, 975, 976, 979, 980, 985, 990, 992, 996, 997,
  A02, AQN, BGT, BUF, CFI, CNF, DGM, DRT, EST, EXP, FFP, FIN, LAS, LCS, LDO, LEO, LET,
  MIS, MNI, MPX, NEG, NID, OBJ, OHC, ONS, ONX, PLR, RSC, SRC, SSD, TOC, UNO, VIT, WII
- new general parameters
  - ignorableFields
  - ignorableRecords
- set a field as DataFields of the record even if it doesn't have definition
- new validators: DateValidator, RegexValidator, RangeValidator