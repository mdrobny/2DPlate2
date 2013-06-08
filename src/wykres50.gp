set term png
set output 'grid50.png'
set size square
set pm3d map
set xrange[0 : 49]
set yrange[0 : 49]
splot 'proj2.txt' u 1:2:3 title 'Siatka' 

set term postscript color
set output 'grid50.eps'
set size square
set pm3d map
set xrange[0 : 49]
set yrange[0 : 49]
splot 'proj2.txt' u 1:2:3 title 'Siatka' 
