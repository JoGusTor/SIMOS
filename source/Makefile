# makefile for source directory

OBJS=branch_phase.o	crown_layer.o	initialize.o	matrix_ops.o	pdf_setup.o	\
solve_canopy.o solve_leaf.o  solve_branch.o\
branch_phase2.o	cyl_size_int.o	leaf_phase.o		\
	cylinf_sub.o	leaf_po_smat.o	ndl_rlgh_smat.o	pdf_sz_setup.o	stokes_sub.o\
dielectric.o	leaf_rlgh_smt.o	needle_phase.o		trunk_layer.o\
format_output.o	lngthncyl_smt.o	pd_funcs.o	res_cyl_smat.o	write_data.o\
ground_layer.o	logo.o		snow_layer.o	write_error.o\
mimics_1_5.o start_loop.o read_input.o kappa_mat_sub.o\
dbl_cmx_fnct.o	dbl_functions.o functions.o	naas_subs.o	vector_ops.o


.f.o:
	f77 -c $<

libs:	$(OBJS)
#	/bin/rm -f libs.a
#	ar cr libs.a `lorder $(OBJS) |tsort`
#	ranlib libs.a

#
#clean:
#	/bin/rm *.o
#


all:	libs make_table make_trunk

make_table:	make_table.o write_error.o
	f77 make_table.o write_error.o -o ../input_skel_dir/make_table
	/bin/rm -f make_table.o


make_trunk:	make_trnkhgt.o
	f77 make_trnkhgt.o -o ../input_skel_dir/make_trnkhgt
	/bin/rm -f make_trnkhgt.o
	
