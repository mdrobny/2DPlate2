set term png
set output 'grid10.png'
set size square
set pm3d map
set xrange[0 : 9]
set yrange[0 : 9]
splot 'proj2.txt' u 1:2:3 title 'Siatka' 

set term postscript color
set output 'grid10.eps'
set size square
set pm3d map
set xrange[0 : 9]
set yrange[0 : 9]
splot 'proj2.txt' u 1:2:3 title 'Siatka' 
