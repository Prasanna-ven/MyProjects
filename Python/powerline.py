#!/usr/bin/python3
# -*- coding: utf-8 -*-
import re
import sys
from powerline_shell import main
val = re.sub(r'(-script\.pyw|\.exe)?$', '', sys.argv[0])
print(" Val : ", val)
