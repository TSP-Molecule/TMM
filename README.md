# ATOM: A Tiny Object Modeler
TSP Spring 2018 project

Project SDK: Java 1.8

Element data from [andrejewski's periodic table](https://github.com/andrejewski/periodic-table) 

# Releases
* Deliverables 1: [Release](https://github.com/TSP-Molecule/ATOM/raw/Deliverables-1.0/ATOM.jar) | [Source](https://github.com/TSP-Molecule/ATOM/tree/Deliverables-1.0/)

# Requirements
To run ATOM, you will need both Python 3.6.x and Java 1.8.x JRE installed on your machine.

***IMPORTANT NOTE***:
If you are on Windows, you will need to set the `PYTHONPATH` environment variable to the location of your Python executable. ***ATOM WILL NOT WORK PROPERLY IF THIS IS NOT DONE***.

![System Variable Screen](https://i.imgur.com/B4FZpKz.png)

# To run ATOM
Commandline compilation isn't currently functioning, but we've built a jar available in the repo, as well as provided one in our deliverables with the most up-to-date functional changes. 

So, the command to run is as follows:
`~$: java -jar ATOM.jar`

To use, simply click any GUI items available. The periodic table popup is available under `Navigation->View Periodic Table` in the menu bar.


# To run Python script for ChemSpider data pull
`~$: python ChemSpider.py -f "chemical name"` #returns the first chemical formula that matches the name provided</br>
  Example name: water, glucose, "nitric acid"</br>
  
`~$: python ChemSpider.py -n "chemical formula"` #returns the first common name that matches the formula provided</br>
  Example formula: C6H12O6, H2O, HCl</br>
  
The flag must come before other arguments.

# Project Screenshots
Main Window
![Main Window](https://i.imgur.com/ect59gc.png)

Rotating the molecular model

<img src="https://i.imgur.com/F4g0Jh9.gif" width="30%" height="30%"/>
<img src="https://i.imgur.com/mWVuCdB.gif" width="30%" height="30%"/>

Parsing a chemical name into formula and atoms

<img src="https://i.imgur.com/yxhulwd.gif" width="100%" height="100%"/>

Interactive Periodic Table

<img src="https://i.imgur.com/lc60jLM.png" width="50%" height="50%"/>
<img src="https://i.imgur.com/b7NeVCB.gif" width="50%" height="50%"/>
