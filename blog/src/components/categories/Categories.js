export default function Categories(props) {

    const categoriesElements = props.data.map(i => <div key={i.id} className="categories__item" >
        <h2 className="category__name">{i.name}</h2>

        <button className="category__deleteButton" onClick={event => props.deleteCategory(event, i.id)}>Delete</button>
    </div>)

    return (
        <div className="categories">
            <h1 className='categories__title'>Categories</h1>
            <div className='categories__container'>
                {categoriesElements}
            </div>
        </div>
    )
}